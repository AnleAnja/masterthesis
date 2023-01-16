<template>
  <transition-group class="photos-container" name="list-animated">
    <photo
      class="list-animated-item"
      v-for="photo in photos"
      :key="photo.id"
      :src="photo.src"
    />
  </transition-group>
</template>

<script>
import Photo from "./PhotoView.vue";
export default {
  components: { Photo },
  props: {
    photos: {
      type: Array,
      default: () => {
        return [];
      },
    },
  },
};
</script>

<style scoped>
.photos-container {
  width: 100%;
  height: 100%;
  display: flex;
  overflow-x: scroll;
  overflow-y: hidden;
  background-color: rgb(20, 20, 20);
  user-select: none;
}

.list-animated-item {
  transition: opacity 0.5s;
}
</style>
