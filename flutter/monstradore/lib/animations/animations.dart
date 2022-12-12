import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Animations extends StatefulWidget {
  const Animations({super.key});

  @override
  State<StatefulWidget> createState() => _AnimationsState();
}

class _AnimationsState extends State<Animations> {  @override

  Widget build(BuildContext context) {
  final theme = Theme.of(context);
  final textTheme = theme.textTheme;

    return Scaffold(
      body: Column(
        children: [
          Text("Animationen", style: textTheme.headline6),
          TextButton(onPressed: () {

          }, child: const Text("Transition")),
          TextButton(onPressed: () {

          }, child: const Text("Hide")),
        ],
      )
    );
  }
}
