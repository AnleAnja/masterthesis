import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Animations extends StatefulWidget {
  const Animations({super.key});

  @override
  State<StatefulWidget> createState() => _AnimationsState();
}

class _AnimationsState extends State<Animations> {
  double size = 128.0;
  bool large = false;
  bool visible = true;
  String hideShowText = "Hide";

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
        body: Center(
            child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 50, 0, 0),
          child: Text("Animationen", style: textTheme.headline6),
        ),
        TextButton(
            onPressed: () => updateSize(), child: const Text("Transition")),
        AnimatedSize(
                curve: Curves.easeIn,
                duration: const Duration(seconds: 1),
                child: Container(
                  color: Colors.grey,
                  height: size,
                  width: size,
                )),
        TextButton(onPressed: () {
          setState(() {
            visible = !visible;
            if (visible) {
              hideShowText = "Hide";
            } else {
              hideShowText = "Show";
            }
          });
        }, child: Text(hideShowText)),
        AnimatedOpacity(
            opacity: visible ? 1.0 : 0.0,
            duration: const Duration(milliseconds: 500),
                child: Container(
                  color: Colors.grey,
                  height: 128.0,
                  width: 128.0,
                )),
      ],
    )));
  }

  void updateSize() {
    setState(() {
      size = large ? 128.0 : 64.0;
      large = !large;
    });
  }
}
