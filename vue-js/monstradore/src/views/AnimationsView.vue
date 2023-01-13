<template>
  <h1>Animationen</h1>
  <v-btn @click="toggleShow">{{ buttonText }}</v-btn>
  <Transition>
    <div v-if="show" class="rectSize" />
  </Transition>
</template>

<script>
export default {
  data() {
    return {
      name: "AnimationsView",
      size: "128",
      buttonText: "Hide",
      show: true,
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
      this.show ? (this.buttonText = "Hide") : (this.buttonText = "Show");
    },
  },
};
</script>

<style scoped>
.rectSize {
  background-color: darkgrey;
  width: 128px;
  height: 128px;
  margin: 10px;
}
/* we will explain what these classes do next! */
.v-enter-active,
.v-leave-active {
  transition: opacity 1s ease;
}
.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
