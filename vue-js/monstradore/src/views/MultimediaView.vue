<template>
  <h1>Audio</h1>
  <v-row>
    <v-btn class="btn" icon @click="play(audio)">
      <v-icon>mdi-play</v-icon>
    </v-btn>
    <v-btn class="btn" icon @click="pause(audio)">
      <v-icon>mdi-pause</v-icon>
    </v-btn>
  </v-row>
  <h1>Video</h1>
  <video controls :width="width" playsinline>
    <source
      src="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
      type="video/mp4"
    />
  </video>
</template>

<script>
export default {
  data() {
    return {
      name: "MultimediaView",
      audio: new Audio(require("../assets/sampleaudio.mp3")),
      width: screen.width,
    };
  },
  methods: {
    play(audio) {
      audio.play();
    },
    pause(audio) {
      audio.pause();
    },
  },
};
</script>

<style scoped>
.btn {
  margin-left: 15px;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
