import 'package:flutter/material.dart';
import 'package:grouped_list/grouped_list.dart';
import 'package:monstradore/gestures/gestures.dart';
import 'package:monstradore/navigation/navigation.dart';
import 'package:monstradore/inputmethods/inputmethods.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'monstradore',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'monstradore'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  final List _features = [
    {'name': 'Reichhaltige UI Elemente', 'group': 'UI / UX', 'order': 0},
    {'name': 'Interaktionsdesign', 'group': 'UI / UX', 'order': 1},
    {'name': 'Gesten', 'group': 'UI / UX', 'order': 2},
    {'name': 'Navigation', 'group': 'UI / UX', 'order': 3},
    {'name': 'Eingabemethoden', 'group': 'UI / UX', 'order': 4},
    {'name': 'Multimedia', 'group': 'UI / UX', 'order': 5},
    {'name': 'Animationen', 'group': 'UI / UX', 'order': 6},
    {'name': '3D Grafiken', 'group': 'UI / UX', 'order': 7},
    {'name': 'Netzwerkcalls', 'group': 'Gerätespezifische Funktionen', 'order': 0},
    {'name': 'Dateizugriff', 'group': 'Gerätespezifische Funktionen', 'order': 1},
    {'name': 'Persistierung', 'group': 'Gerätespezifische Funktionen', 'order': 2},
    {'name': 'Zugriff auf native Anwendungen', 'group': 'Gerätespezifische Funktionen', 'order': 3},
    {'name': 'Kamera', 'group': 'Gerätespezifische Funktionen', 'order': 4},
    {'name': 'GPS', 'group': 'Gerätespezifische Funktionen', 'order': 5},
    {'name': 'Beschleunigung', 'group': 'Gerätespezifische Funktionen', 'order': 6},
    {'name': 'Fingerabdruck / Face ID', 'group': 'Gerätespezifische Funktionen', 'order': 7},
    {'name': 'Primzahlberechnung', 'group': 'Algorithmen', 'order': 0},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body:
        GroupedListView<dynamic, String>(
          elements: _features,
          groupBy: (element) => element['group'],
          groupComparator: (value1,
              value2) => value2.compareTo(value1),
          itemComparator: (item1, item2) =>
              item1['order'].compareTo(item2['order']),
          order: GroupedListOrder.ASC,
          // useStickyGroupSeparators: true,
          groupSeparatorBuilder: (String value) => Padding(
            padding: const EdgeInsets.all(10.0),
            child: Text(
              value,
              textAlign: TextAlign.left,
              style: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold
              ),
            ),
          ),
          itemBuilder: (c, element) {
            return GestureDetector(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) {
                      switch (element['name']) {
                        case 'Eingabemethoden':
                          return const InputMethods();
                        case 'Gesten':
                          return const Gestures();
                        case 'Navigation':
                          return const Navigation();
                        default:
                          return const Text("Unbekanntes Feature");
                      }
                    }),
                  );
                },
                child: Container(
              margin: const EdgeInsets.symmetric(horizontal: 10.0,
                  vertical: 10.0),
              child: Text(
                  element['name'],
                  style: const TextStyle(fontSize: 16),
                ),
            ));
          },
        ),
        );
  }
}
