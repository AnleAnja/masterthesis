import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'dart:io' show Platform;

class InteractionDesign extends StatefulWidget {
  const InteractionDesign({super.key});

  @override
  State<StatefulWidget> createState() => _InteractionDesignState();
}

class _InteractionDesignState extends State<InteractionDesign> {
  var items =
      List.generate(25, (index) => "Dieses Element hat den Index $index.");
  late Offset tapPosition;
  late OverlayEntry popupDialog = OverlayEntry(builder: (BuildContext context) {
    throw Exception();
  });

  @override
  Widget build(BuildContext context) {
    if (Platform.isIOS) {
      return Scaffold(
        body: ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            final item = items[index];
            return Dismissible(
                key: Key(item),
                onDismissed: (direction) {
                  setState(() {
                    items.removeAt(index);
                  });
                  ScaffoldMessenger.of(context)
                      .showSnackBar(SnackBar(content: Text('$item gelöscht')));
                },
                background: Container(color: Colors.red),
                child: ListTile(
                  title: Text(items[index]),
                )
                /*ListView.builder(
                  itemCount: items.length,
                  itemBuilder: (context, index) {
                    final item = items[index];
                    return GestureDetector(
                        onLongPress: () {
                          popupDialog = createPopupDialog(item);
                          Overlay.of(context)?.insert(popupDialog);
                        },
                        onLongPressEnd: (details) => popupDialog.remove(),
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(item)
                    )
                    );
                  }
              ),*/
                );
          },
        ),
      );
    }
    if (Platform.isAndroid) {
      return Scaffold(
          body: ListView.builder(
              itemCount: items.length,
              itemBuilder: (context, index) {
                final item = items[index];
                return GestureDetector(
                    onTapDown: (details) {
                      tapPosition = getTapPosition(details);
                    },
                    onLongPress: () {
                      showContextMenu(context, tapPosition, index);
                    },
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(item),
                    ));
              }));
    }
    throw Exception();
  }

  Offset getTapPosition(TapDownDetails details) {
    final RenderBox referenceBox = context.findRenderObject() as RenderBox;
    return referenceBox.globalToLocal(details.globalPosition);
  }

  void showContextMenu(
      BuildContext context, Offset tapPosition, int index) async {
    final RenderObject? overlay =
        Overlay.of(context)?.context.findRenderObject();
    final result = await showMenu(
        context: context,
        position: RelativeRect.fromRect(
            Rect.fromLTWH(tapPosition.dx, tapPosition.dy, 30, 30),
            Rect.fromLTWH(0, 0, overlay!.paintBounds.size.width,
                overlay.paintBounds.size.height)),
        items: [
          const PopupMenuItem(
            value: "delete",
            child: Text("Löschen"),
          ),
        ]);
    setState(() {
      if (result == "delete") {
        items.removeAt(index);
      }
    });
  }

  OverlayEntry createPopupDialog(String item) {
    return OverlayEntry(
        builder: (context) => Container(
              padding: const EdgeInsets.symmetric(horizontal: 16.0),
              child: ClipRRect(
                  borderRadius: BorderRadius.circular(16.0),
                  child: Text(item,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ))),
            ));
  }
}
