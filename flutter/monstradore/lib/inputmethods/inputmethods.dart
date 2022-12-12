import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class InputMethods extends StatelessWidget {
  const InputMethods({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.fromLTRB(0, 50, 0, 0),
            child: Text("Texteingabe", style: textTheme.headline6),
          ),
          const TextField(
            decoration:
                InputDecoration(border: OutlineInputBorder(), labelText: "Label"),
          ),
          Text("Passworteingabe", style: textTheme.headline6),
          const TextField(
            obscureText: true,
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: "Password Label",
            ),
          )
        ],
        ),
      )
    );
  }
}
