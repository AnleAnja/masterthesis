<template>
  <v-btn @click="requestPermissionAndStart">Request Permissions</v-btn>
  <h1>Beschleunigung</h1>
  <p>Roll: {{ this.acceleration.x }}</p>
  <p>Pitch: {{ this.acceleration.y }}</p>
  <p>Yaw: {{ this.acceleration.z }}</p>
</template>

<script>
export default {
  data() {
    return {
      name: "AccelerationView",
      acceleration: {
        x: 0.0,
        y: 0.0,
        z: 0.0,
      },
    };
  },
  created() {
    this.startListening();
  },
  methods: {
    startListening() {
      window.ondevicemotion = (event) => {
        this.acceleration.x = event.acceleration.x;
        this.acceleration.y = event.acceleration.y;
        this.acceleration.z = event.acceleration.z;
      };
    },
    requestPermissionAndStart(e) {
      e.preventDefault();

      try {
        if (
          window.DeviceMotionEvent &&
          typeof window.DeviceMotionEvent.requestPermission === "function"
        ) {
          window.DeviceMotionEvent.requestPermission();
        }
      } catch (e) {
        console.log(e);
      }

      this.startListening();
    },
  },
};
</script>

<style scoped></style>
