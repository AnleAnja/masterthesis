import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:grouped_list/grouped_list.dart';
import 'package:monstradore/multimedia/multimedia.dart';
import 'package:monstradore/animations/animations.dart';
import 'package:monstradore/fileaccess/fileaccess.dart';
import 'package:monstradore/gestures/gestures.dart';
import 'package:monstradore/hardwarefunctions/camera.dart';
import 'package:monstradore/navigation/navigation.dart';
import 'package:monstradore/inputmethods/inputmethods.dart';
import 'package:monstradore/networkcall/networkcall.dart';
import 'package:monstradore/objects/objects.dart';
import 'package:monstradore/performance/performance.dart';
import 'package:monstradore/persistence/persistence.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final cameras = await availableCameras();
  final firstCamera = cameras.first;
  runApp(MyApp(camera: firstCamera));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key, required this.camera});

  final CameraDescription camera;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'monstradore',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'monstradore', camera: camera),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title, required this.camera});

  final String title;
  final CameraDescription camera;

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
      body: GroupedListView<dynamic, String>(
        elements: _features,
        groupBy: (element) => element['group'],
        groupComparator: (value1, value2) => value2.compareTo(value1),
        itemComparator: (item1, item2) =>
            item1['order'].compareTo(item2['order']),
        order: GroupedListOrder.ASC,
        // useStickyGroupSeparators: true,
        groupSeparatorBuilder: (String value) => Padding(
            padding: const EdgeInsets.all(10.0),
            child: Text(
              value,
              textAlign: TextAlign.left,
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ))),
        itemBuilder: (c, element) {
          return GestureDetector(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) {
                    switch (element['name']) {
                      case 'Gesten':
                        return const Gestures();
                      case 'Navigation':
                        return const Navigation();
                      case 'Eingabemethoden':
                        return const InputMethods();
                      case 'Animationen':
                        return const Animations();
                      case 'Multimedia':
                        return const Multimedia();
                      case '3D Grafiken':
                        return const Objects();
                      case 'Netzwerkcalls':
                        return const NetworkCall();
                      case 'Dateizugriff':
                        return FileAccess(storage: FileStorage());
                      case 'Persistierung':
                        return const Persistence();
                      case 'Kamera':
                        return CameraWidget(camera: widget.camera);
                      case 'Primzahlberechnung':
                        return const Prime();
                      default:
                        return const Text("Nicht nativ verfügbar");
                    }
                  }),
                );
              },
              child: Container(
                margin: const EdgeInsets.symmetric(
                    horizontal: 10.0, vertical: 10.0),
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
