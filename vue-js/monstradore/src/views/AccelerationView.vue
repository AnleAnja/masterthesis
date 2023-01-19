<template>
  <v-btn @click="startAcceleration">Start</v-btn>
  <v-btn @click="stopAcceleration">Stop</v-btn>
  <h1>Beschleunigung</h1>
  <p>Roll: {{ this.acceleration.x }}</p>
  <p>Pitch: {{ this.acceleration.y }}</p>
  <p>Yaw: {{ this.acceleration.z }}</p>
</template>

<script>
import { Motion } from "@capacitor/motion";

export default {
  data() {
    return {
      name: "AccelerationView",
      acceleration: {
        x: 0,
        y: 0,
        z: 0,
      },
      accelHandler: null,
    };
  },
  methods: {
    async startAcceleration() {
      if (window.DeviceMotionEvent) {
        console.log("set acc handler");
        window.addEventListener("devicemotion", this.handleMotion, true);
        self.supported = true;
      }
      console.log(window.DeviceMotionEvent);

      window.addEventListener(
        "compassneedscalibration",
        function (event) {
          self.supported = true;
          alert("Your compass needs calibrating!");
          event.preventDefault();
        },
        true
      );
      // try {
      //   await DeviceMotionEvent.requestPermission();
      // } catch (e) {
      //   // Handle error
      //   console.log(e);
      //   return;
      // }
      //
      // // Once the user approves, can start listening:
      // this.accelHandler = await Motion.addListener("accel", (event) => {
      //   console.log("Device motion event:", event);
      //   this.handleMotion(event);
      // });

      console.log(this.accelHandler);
    },
    handleMotion(event) {
      this.acceleration.x = event.acceleration.x;
      this.acceleration.y = event.acceleration.y;
      this.acceleration.z = event.acceleration.z;
    },
    stopAcceleration() {
      if (this.accelHandler) {
        this.accelHandler.remove();
      }
    },
    removeListeners() {
      Motion.removeAllListeners();
    },
  },
};
</script>

<style scoped></style>
