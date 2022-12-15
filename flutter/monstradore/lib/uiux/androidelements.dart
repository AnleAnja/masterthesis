import 'package:flutter/material.dart';
import 'package:material_segmented_control/material_segmented_control.dart';

class AndroidElements extends StatefulWidget {
  const AndroidElements({super.key});

  @override
  State<StatefulWidget> createState() => _AndroidElementsState();
}

class _AndroidElementsState extends State<AndroidElements> {
  int _selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Top App Bar"),
      ),
      drawer: const Drawer(
        child: Padding(
          padding: EdgeInsets.all(8.0),
          child: Text("Navigation Drawer Content"),
        ),
      ),
      body: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            NavigationRail(
              destinations: const [
                NavigationRailDestination(
                  icon: Icon(Icons.home_outlined),
                  selectedIcon: Icon(Icons.home),
                  label: Text('First'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.home_outlined),
                  selectedIcon: Icon(Icons.home),
                  label: Text('Second'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.home_outlined),
                  selectedIcon: Icon(Icons.home),
                  label: Text('Third'),
                ),
              ],
              selectedIndex: _selectedIndex,
              onDestinationSelected: (int index) {
                setState(() {
                  _selectedIndex = index;
                });
              },
            ),
            const VerticalDivider(thickness: 1, width: 1),
            SingleChildScrollView(
                child: Column(
              children: const [
                AndroidNavElements(),
                AndroidBasicElements(),
                AndroidInteractiveElements(),
              ],
            ))
          ]),
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        child: const Icon(Icons.home),
      ),
      bottomNavigationBar: const BottomAppBar(
        shape: null,
        color: Colors.blue,
        child: SizedBox(
          height: 50.0,
          child: Spacer(),
        ),
      ),
    );
  }
}

class AndroidNavElements extends StatefulWidget {
  const AndroidNavElements({super.key});

  @override
  State<StatefulWidget> createState() => _AndroidNavElementsState();
}

class _AndroidNavElementsState extends State<AndroidNavElements> {
  bool showLeading = false;
  bool showTrailing = false;
  NavigationRailLabelType labelType = NavigationRailLabelType.all;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(children: [
      Text("Struktur und Navigation", style: textTheme.headline5),
      Text("App Bar Top", style: textTheme.headline6),
      const Text("Verfügbar, s.o.", style: TextStyle(color: Colors.blue)),
      Text("App Bar Bottom", style: textTheme.headline6),
      const Text("Verfügbar, s.u.", style: TextStyle(color: Colors.blue)),
      Text("Navigation Drawer", style: textTheme.headline6),
      const Text("Verfügbar, swipe left to right",
          style: TextStyle(color: Colors.blue)),
      Text("Navigation Rail", style: textTheme.headline6),
      const Text("Verfügbar, s. links", style: TextStyle(color: Colors.blue)),
    ]);
  }
}

class AndroidBasicElements extends StatefulWidget {
  const AndroidBasicElements({super.key});

  @override
  State<StatefulWidget> createState() => _AndroidBasicElementsState();
}

class _AndroidBasicElementsState extends State<AndroidBasicElements> {
  final Map<int, Widget> _children = {
    0: const Padding(
        padding: EdgeInsets.symmetric(horizontal: 8.0), child: Text("Item 1")),
    1: const Padding(
        padding: EdgeInsets.symmetric(horizontal: 8.0), child: Text("Item 2")),
    2: const Padding(
        padding: EdgeInsets.symmetric(horizontal: 8.0), child: Text("Item 3"))
  };
  int _currentSelection = 0;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(
      children: [
        Text("Grundlegende", style: textTheme.headline5),
        Text("Android Elemente", style: textTheme.headline5),
        Text("Card", style: textTheme.headline6),
        const Card(
          child: Padding(
            padding: EdgeInsets.all(8.0),
            child: Text("Card Content"),
          ),
        ),
        Text("Chip", style: textTheme.headline6),
        const Chip(label: Text("Chip Content")),
        Text("Snackbar", style: textTheme.headline6),
        TextButton(
            onPressed: () {
              const snackBar = SnackBar(content: Text("Snackbar Content"));
              ScaffoldMessenger.of(context).showSnackBar(snackBar);
            },
            child: const Text("Snackbar")),
        Text("Segmented Buttons", style: textTheme.headline6),
        MaterialSegmentedControl(
          children: _children,
          selectionIndex: _currentSelection,
          borderColor: Colors.grey,
          selectedColor: Colors.blue,
          unselectedColor: Colors.white,
          borderRadius: 32.0,
          onSegmentChosen: (index) {
            setState(() {
              _currentSelection = index;
            });
          },
        ),
        Text("Floating Action Buttons", style: textTheme.headline6),
        const Text("Verfügbar, s.u.", style: TextStyle(color: Colors.blue))
      ],
    );
  }
}

class AndroidInteractiveElements extends StatefulWidget {
  const AndroidInteractiveElements({super.key});

  @override
  State<StatefulWidget> createState() => _AndroidInteractiveElementsState();
}

class _AndroidInteractiveElementsState
    extends State<AndroidInteractiveElements> {
  bool isChecked = false;
  int item = 1;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Column(children: [
      Text("Interaktive", style: textTheme.headline5),
      Text("Android Elemente", style: textTheme.headline5),
      Text("Checkbox", style: textTheme.headline6),
      Checkbox(
          value: isChecked,
          onChanged: (bool? value) {
            setState(() {
              isChecked = value!;
            });
          }),
      Text("Radio Buttons", style: textTheme.headline6),
      Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Radio(
              value: 0,
              groupValue: item,
              onChanged: (index) {
                setState(() {
                  item = index!;
                });
              }),
          Radio(
              value: 1,
              groupValue: item,
              onChanged: (index) {
                setState(() {
                  item = index!;
                });
              }),
        ],
      ),
      Text("Time Picker", style: textTheme.headline6),
      TextButton(
          child: const Text("Time Picker"),
          onPressed: () => displayTimePicker(context)),
      Text("Date Picker", style: textTheme.headline6),
      TextButton(
          child: const Text("Date Picker"),
          onPressed: () => displayDatePicker(context))
    ]);
  }

  Future displayDatePicker(BuildContext context) async {
    await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
    );
  }

  Future displayTimePicker(BuildContext context) async {
    await showTimePicker(context: context, initialTime: TimeOfDay.now());
  }
}
