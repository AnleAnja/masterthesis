<template>
  <div v-if="photo" class="screen">
    <video
      autoplay
      playsinline
      :class="facingMode === 'user' ? 'front' : ''"
      ref="video"
      class="video"
    />
    <canvas style="display: none" ref="canva" />
    <div class="photo-button-container">
      <v-btn class="button photo-button" @click="TakePhoto" icon="mdi-camera" />
    </div>
  </div>
  <div v-if="photos.length !== 0" class="screen">
    <photos-gallery class="video" :photos="photos" />
  </div>
</template>

<script>
import PhotosGallery from "./GalleryView.vue";

export default {
  name: "CameraView",
  components: {
    PhotosGallery,
  },
  data() {
    return {
      photos: [],
      mediaStream: null,
      videoDevices: [],
      facingMode: "environment",
      counter: 0,
      switchingCamera: false,
      photo: true,
    };
  },
  methods: {
    async StartRecording(facingMode) {
      this.facingMode = facingMode;
      let video = this.$refs.video;
      this.mediaStream = await navigator.mediaDevices.getUserMedia({
        video: { facingMode: facingMode },
      });
      video.srcObject = this.mediaStream;
      return await video.play();
    },
    async TakePhoto() {
      let video = this.$refs.video;
      let canva = this.$refs.canva;
      let width = video.videoWidth;
      let height = video.videoHeight;
      canva.width = width;
      canva.height = height;
      let ctx = canva.getContext("2d");
      ctx.save();

      if (this.facingMode === "user") {
        ctx.scale(-1, 1);
        ctx.drawImage(video, width * -1, 0, width, height);
      } else {
        ctx.drawImage(video, 0, 0);
      }

      ctx.restore();

      this.photos.push({
        id: this.counter++,
        src: canva.toDataURL("image/png"),
      });
      this.photo = false;
    },
  },
  async mounted() {
    const devices = await navigator.mediaDevices.enumerateDevices();
    this.videoDevices = devices.filter((d) => d.kind === "videoinput");
    await this.StartRecording(
      this.videoDevices.length === 1 ? "user" : "environment"
    );
  },
};
</script>

<style scoped>
.wrapper {
  background-color: black;
  width: 100%;
  height: 100%;
  flex-direction: row;
  align-content: center;
  justify-content: center;
}

.video {
  height: 100%;
  position: relative;
  z-index: 1;
  margin-left: auto;
  margin-right: auto;
  display: block;
  overflow-x: hidden;
  max-width: 100%;
  min-width: 50%;
}

.photo-button-container {
  position: fixed;
  bottom: 5%;
  z-index: 5;
  width: 100%;
  height: 10%;
  display: flex;
  justify-content: center;
}

.photo-button {
  width: 10vh;
  height: 10vh;
  border-radius: 100%;
}

.photo-button {
  font-size: 4vh;
  color: black;
}

.screen {
  height: 100%;
  width: 100%;
  overflow-x: hidden;
}
</style>
