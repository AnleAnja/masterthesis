<template>
  <h1>Audio</h1>
  <button @click="playSound">Say hello</button>
  <h1>Video</h1>
</template>

<script>
import sound from "../assets/sampleaudio.mp3";

export default {
  data() {
    return {
      name: "MultimediaView",
    };
  },
  methods: {
    playSound() {
      const audio = new Audio(sound);
      audio.play();
    },
  },
};
</script>

<style scoped></style>
