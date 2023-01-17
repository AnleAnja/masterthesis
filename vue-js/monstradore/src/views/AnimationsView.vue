<template>
  <h1>Animationen</h1>
  <v-btn @click="sizeTransition">Transition</v-btn>
  <div
    id="transitionDiv"
    ref="transitionDiv"
    v-if="size"
    :style="{ width: `${this.size}px`, height: `${this.size}px` }"
    class="rect"
  />
  <v-btn @click="toggleShow">{{ buttonText }}</v-btn>
  <div class="rect rectSize" :style="{ opacity: this.show ? 1 : 0 }" />
</template>

<script>
export default {
  data() {
    return {
      name: "AnimationsView",
      size: 128,
      buttonText: "Hide",
      show: true,
    };
  },
  methods: {
    sizeTransition() {
      this.size === 128 ? (this.size = 64) : (this.size = 128);
    },
    toggleShow() {
      this.show = !this.show;
      this.show ? (this.buttonText = "Hide") : (this.buttonText = "Show");
    },
  },
};
</script>

<style scoped>
.rectSize {
  width: 128px;
  height: 128px;
}

.rect {
  background-color: darkgrey;
  margin: 10px;
  transition: all 1s ease;
}
</style>
