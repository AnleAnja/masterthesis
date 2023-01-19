<template>
  <h1>GPS Sensor</h1>
  <p>Longitude: {{ this.longitude }}</p>
  <p>Latitude: {{ this.latitude }}</p>
</template>

<script>
export default {
  data() {
    return {
      name: "GPSView",
      latitude: 0,
      longitude: 0,
    };
  },
  created() {
    const success = (position) => {
      this.latitude = position.coords.latitude;
      this.longitude = position.coords.longitude;

      // Do something with the position
    };

    const error = (err) => {
      console.log(err);
    };

    // This will open permission popup
    navigator.geolocation.getCurrentPosition(success, error);
  },
};
</script>

<style scoped></style>
