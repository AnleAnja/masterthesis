export default function worker() {
  const calcPrime = (n) => {
    let num = 1;
    let count = 0;
    while (count < n) {
      num++;
      let i = 2;
      while (i <= num) {
        if (num % i === 0) {
          break;
        }
        i++;
      }
      if (i === num) {
        count++;
      }
    }
    return num;
  };

  onmessage = (event) => {
    if (
      event.data.key !== "working" &&
      event.data.key !== "nums" &&
      event.data.args
    ) {
      postMessage({ key: "working", value: true });
      postMessage({
        key: "nums",
        value: calcPrime(...event.data.args),
      });
      postMessage({ key: "working", value: false });
    }
  };
}
