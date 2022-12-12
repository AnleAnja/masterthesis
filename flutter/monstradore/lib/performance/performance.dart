import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Prime extends StatefulWidget {
  const Prime({super.key});

  @override
  State<StatefulWidget> createState() => _PrimeState();
}

class _PrimeState extends State<Prime> {
  int value = 20000;
  int result = 0;
  int time = 0;
  double currentSliderValue = 0.0;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
        body: Column(children: [
      Padding(
        padding: const EdgeInsets.fromLTRB(0, 50, 0, 0),
        child: Text("Performance", style: textTheme.headline6),
      ),
      const Text("Die wievielte Primzahl soll berechnet werden?"),
      TextFormField(
        decoration: const InputDecoration(
            border: OutlineInputBorder(), labelText: "Zahl eingeben"),
        initialValue: value.toString(),
        onSaved: (String? newValue) {
          if (newValue == null) {
            value = 0;
          } else {
            value = int.parse(newValue);
          }
        },
      ),
      TextButton(
          onPressed: () {
            var startTime = DateTime.now().millisecondsSinceEpoch;
            result = primeCalc(value);
            var endTime = DateTime.now().millisecondsSinceEpoch;
            setState(() {
              result = result;
              time = (endTime - startTime) ~/ 1000;
            });
          },
          child: const Text("Berechnen")),
      Text("Ergebnis: $result"),
      Text("Benötigte Zeit: $time Sekunden"),
      Slider(
        value: currentSliderValue,
        max: 100,
        onChanged: (double value) {
          setState(() {
            currentSliderValue = value;
          });
        },
      ),
    ]));
  }

  int primeCalc(int n) {
    var num = 1;
    var count = 0;
    while (count < n) {
      num++;
      var i = 2;
      while (i <= num) {
        if (num % i == 0) {
          break;
        }
        i++;
      }
      if (i == num) {
        count++;
      }
    }
    return num;
  }
}
