<template>
  <h1>Search Bar</h1>
  <p style="color: darkred">Nicht nativ verfügbar</p>
  <h1>Picker</h1>
  <VueScrollPicker :options="options" />
</template>

<script>
export default {
  data() {
    return {
      name: "IOSElementsView",
      options: ["Item 1", "Item 2", "Item 3"],
    };
  },
};
</script>

<style scoped></style>
