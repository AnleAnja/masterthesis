import PinchZoom from 'vue-pinch-zoom';

<template>
  <div class="center">
    <pinch-zoom>
      <img class="img" src="../assets/sample.jpeg" alt="image" />
    </pinch-zoom>
  </div>
</template>

<script>
export default {
  name: "GesturesView",
};
</script>

<style scoped>
.center {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-content: center;
  justify-content: center;
}

.img {
  display: block;
  margin: auto;
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
