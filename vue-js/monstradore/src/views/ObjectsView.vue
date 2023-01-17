<template>
  <model-obj
    src="/obj/sampleobject.obj"
    mtl="/obj/sampleobject.mtl"
  ></model-obj>
</template>
<script>
import { ModelObj } from "vue-3d-model";
export default {
  name: "ObjectsView",
  components: { ModelObj },
};
</script>

<style scoped></style>
