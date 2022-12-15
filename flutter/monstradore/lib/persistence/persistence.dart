import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Persistence extends StatefulWidget {
  const Persistence({super.key});

  @override
  State<Persistence> createState() => _PersistenceState();
}

class _PersistenceState extends State<Persistence> {
  TextEditingController textController = TextEditingController();
  List<String> _users = [];

  @override
  void initState() {
    super.initState();
    _loadUsers();
  }

  Future<void> _loadUsers () async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
        _users = prefs.getStringList('users') ?? [];
    });
  }

  Future<void> _setUsers() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      _users.add(textController.text);
      prefs.setStringList('users', _users);
    });
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Persistenz", style: textTheme.headline6),
            const Text("Username eingeben"),
            TextFormField(
              decoration:
              const InputDecoration(border: OutlineInputBorder(), labelText: "Label"),
              controller: textController,
            ),
            TextButton(onPressed: () {
              _setUsers();
            }, child: const Text("User speichern")),
            Column(
              children: _users.map((e) => Text(e)).toList(),
            )
          ],
        ),
    );
  }
}