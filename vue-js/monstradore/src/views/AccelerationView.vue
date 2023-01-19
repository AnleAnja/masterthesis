<template>
  <v-btn @click="startAcceleration">Start</v-btn>
  <v-btn @click="stopAcceleration">Stop</v-btn>
  <h1>Beschleunigung</h1>
  <p>Roll: {{ this.acceleration.x }}</p>
  <p>Pitch: {{ this.acceleration.y }}</p>
  <p>Yaw: {{ this.acceleration.z }}</p>
  <span>{{ this.log }}</span>
  <p>{{ this.interval }}</p>
  <!--UseDeviceMotion v-slot="{ accelerationIncludingGravity, interval }">
    acceleration: {{ accelerationIncludingGravity }}, interval: {{ interval }}
  </UseDeviceMotion-->
</template>

<script>
// import { Motion } from "@capacitor/motion";
// import { UseDeviceMotion } from "@vueuse/components";

export default {
  // components: {
  //   UseDeviceMotion,
  // },
  data() {
    return {
      name: "AccelerationView",
      acceleration: {
        x: 0.0,
        y: 0.0,
        z: 0.0,
      },
      accelHandler: null,
      log: "testjkjh",
      isRunning: false,
      interval: "0.0",
    };
  },
  methods: {
    startAcceleration(event) {
      event.preventDefault();
      console.log(window);
      this.log = "start acceleration";
      window.ondevicemotion = (e) => {
        console.log(e);
      };
      // if (
      //   window.DeviceMotionEvent &&
      //   typeof window.DeviceMotionEvent.requestPermission === "function"
      // ) {
      //   this.log = "set acc handler";
      //   window.DeviceMotionEvent.requestPermission();
      // }
      //
      // window.addEventListener("devicemotion", this.handleMotion);
      // this.log = "event set";
    },
    handleMotion(event) {
      console.log(event);
      this.acceleration.x = event.acceleration.x;
      this.acceleration.y = event.acceleration.y;
      this.acceleration.z = event.acceleration.z;
      this.log = event.toString();
      this.interval = event.interval.toString();
    },
    stopAcceleration() {
      if (this.accelHandler) {
        this.accelHandler.remove();
      }
    },
  },
};
</script>

<style scoped></style>
