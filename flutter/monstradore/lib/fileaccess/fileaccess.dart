import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

class FileStorage {
  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();
    return directory.path;
  }

  Future<File> get _localFile async {
    final path = await _localPath;
    return File('$path/samplefile.txt');
  }

  Future<String> readFile() async {
    try {
      final file = await _localFile;
      final contents = await file.readAsString();
      return contents;
    } catch (e) {
      return "Error";
    }
  }

  Future<File> writeFile(String content) async {
    final file = await _localFile;
    return file.writeAsString(content);
  }
}

class FileAccess extends StatefulWidget {
  const FileAccess({super.key, required this.storage});

  final FileStorage storage;

  @override
  State<FileAccess> createState() => _FileAccessState();
}

class _FileAccessState extends State<FileAccess> {
  TextEditingController textController = TextEditingController();
  String _content = "";

  @override
  void dispose() {
    textController.dispose();
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    widget.storage.readFile().then((value) {
      setState(() {
        _content = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
      body: Column(
        children: [
          Text("Dateizugriff", style: textTheme.headline6),
          const Text("Text in Datei schreiben"),
          TextFormField(
            decoration:
            const InputDecoration(border: OutlineInputBorder(), labelText: "Label"),
            controller: textController,
          ),
          Row(
            children: [
              TextButton(onPressed: () {
                widget.storage.writeFile(textController.text);
              }, child: const Text("Datei speichern")),
              TextButton(onPressed: () {
                widget.storage.readFile().then((value) {
                  setState(() {
                    _content = value;
                  });
                });
              }, child: const Text("Datei laden"))
            ],
          ),
          Text(_content)
        ]
      ),
    );
  }
}