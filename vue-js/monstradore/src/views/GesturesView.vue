<template>
  <div class="center">
    <v-zoomer>
      <img class="img" src="../assets/sample.jpeg" alt="image" />
    </v-zoomer>
  </div>
</template>

<script>
import VueZoomer from "vue-zoomer";

export default {
  components: {
    VZoomer: VueZoomer.Zoomer,
  },
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
  /*display: block;*/
  /*margin: auto;*/
  /*width: 100%;*/
  /*position: absolute;*/
  /*top: 50%;*/
  /*left: 50%;*/
  /*transform: translate(-50%, -50%);*/
  object-fit: contain;
  width: 100%;
  height: 100%;
}
</style>
