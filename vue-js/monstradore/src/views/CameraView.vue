<template>
  <div v-if="photo">
    <video
      class="video"
      :class="facingMode === 'user' ? 'front' : ''"
      ref="video"
    />
    <canvas style="display: none" ref="canva" />
    <div class="photo-button-container">
      <v-btn class="button photo-button" @click="TakePhoto">
        <v-icon pack="fas" icon="camera" />
      </v-btn>
    </div>
  </div>
  <photos-gallery v-if="photos.length !== 0" class="gallery" :photos="photos" />
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
  justify-items: center;
}

.video {
  height: 100%;
}

.photo-button-container {
  z-index: 5;
  width: 100vw;
  height: 10vh;
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

.gallery {
  height: 100%;
}
</style>
