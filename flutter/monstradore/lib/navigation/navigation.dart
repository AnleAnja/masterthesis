import 'package:flutter/material.dart';

class Navigation extends StatefulWidget {
  const Navigation({super.key});

  @override
  State<StatefulWidget> createState() => _NavigationState();
}

class _NavigationState extends State<Navigation> {
  int _selectedIndex = 0;

  static const List<Widget> _widgetOptions = <Widget>[
    Text("Item 1"),
    Text("Item 2"),
    Text("Item 3"),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  TabBar get tabBar => const TabBar(tabs: [
        Tab(text: "Tab 1"),
        Tab(text: "Tab 2"),
        Tab(text: "Tab 3"),
      ]);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: PreferredSize(
              preferredSize: tabBar.preferredSize,
              child: Material(color: Colors.blue, child: tabBar)),
          body: Column(
            children: [
              const SizedBox(
                  height: 100,
                  child: TabBarView(
                    children: [
                      Center(child: Text("Tab 1")),
                      Center(child: Text("Tab 2")),
                      Center(child: Text("Tab 3"))
                    ],
                  )),
              _widgetOptions.elementAt(_selectedIndex)
            ],
          ),
          bottomNavigationBar: BottomNavigationBar(
            items: const <BottomNavigationBarItem>[
              BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'Item 1',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'Item 2',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'Item 3',
              ),
            ],
            currentIndex: _selectedIndex,
            selectedItemColor: Colors.blue,
            onTap: _onItemTapped,
          ),
        ),
      ),
    );
  }
}
