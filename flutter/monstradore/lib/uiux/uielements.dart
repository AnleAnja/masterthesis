import 'package:flutter/material.dart';
import 'package:monstradore/uiux/ioselements.dart';

import 'androidelements.dart';

class UIElements extends StatelessWidget {
  const UIElements({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
        body: Padding(
      padding: const EdgeInsets.all(8.0),
      child: SingleChildScrollView(
        child: Column(
          children: [
            Text("Reichhaltige UI Elemente", style: textTheme.headline4),
            const BasicElements(),
            const StateElements(),
            const SpecificElements(),
            const AdvancedElements()
          ],
        ),
      ),
    ));
  }
}

class BasicElements extends StatelessWidget {
  const BasicElements({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(
      children: [
        Text("Grundlegende Elemente", style: textTheme.headline5),
        Text("Text", style: textTheme.headline6),
        const Text(
            "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua."),
        Text("Bild", style: textTheme.headline6),
        const Image(image: AssetImage('assets/sample.jpeg')),
        Text("Liste", style: textTheme.headline6),
        ListView(
          shrinkWrap: true,
          children: List.generate(5, (index) => Text("Item: $index")),
        ),
        Text("Button", style: textTheme.headline6),
        TextButton(
          style: ButtonStyle(
            foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
          ),
          onPressed: () {},
          child: const Text('Button'),
        ),
        Text("Icon Button", style: textTheme.headline6),
        IconButton(onPressed: () {}, icon: const Icon(Icons.home)),
      ],
    );
  }
}

class StateElements extends StatefulWidget {
  const StateElements({super.key});

  @override
  State<StatefulWidget> createState() => _StateElementsState();
}

class _StateElementsState extends State<StateElements> {
  double currentSliderValue = 0.0;
  bool on = true;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(
      children: [
        Text("Elemente mit Statusverwaltung", style: textTheme.headline5),
        Text("Slider", style: textTheme.headline6),
        Slider(
          value: currentSliderValue,
          max: 100,
          onChanged: (double value) {
            setState(() {
              currentSliderValue = value;
            });
          },
        ),
        Text("Textfeld", style: textTheme.headline6),
        const TextField(
            decoration: InputDecoration(border: OutlineInputBorder())),
        Text("Switch / Toggle", style: textTheme.headline6),
        Switch(
            value: on,
            onChanged: (bool value) {
              setState(() {
                on = value;
              });
            })
      ],
    );
  }
}

class SpecificElements extends StatelessWidget {
  const SpecificElements({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(children: [
      Text("Elemente mit Statusverwaltung", style: textTheme.headline5),
      Row(
        children: [
          TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) => const AndroidElements()),
              );
            },
            child: const Text('Android'),
          ),
          TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const iOSElements()),
              );
            },
            child: const Text('iOS'),
          ),
        ],
      )
    ]);
  }
}

class AdvancedElements extends StatelessWidget {
  const AdvancedElements({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(children: [
      Text("Fortgeschrittene Elemente", style: textTheme.headline5),
      Row(children: [
        PopupMenuButton(
          itemBuilder: (BuildContext context) => <PopupMenuEntry<Text>>[
            const PopupMenuItem<Text>(
                value: Text("Menu Item"), child: Text("Menu Item"))
          ],
          child: const Text("Menu"),
        ),
        TextButton(
            onPressed: () => showDialog<String>(
                  context: context,
                  builder: (BuildContext context) => AlertDialog(
                    title: const Text('Dialog Title'),
                    content: const Text('Dialog Text'),
                    actions: <Widget>[
                      TextButton(
                        onPressed: () => Navigator.pop(context, 'Cancel'),
                        child: const Text('Cancel'),
                      ),
                      TextButton(
                        onPressed: () => Navigator.pop(context, 'OK'),
                        child: const Text('OK'),
                      ),
                    ],
                  ),
                ),
            child: const Text("Dialog"))
      ])
    ]);
  }
}
