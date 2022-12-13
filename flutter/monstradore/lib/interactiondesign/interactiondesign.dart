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
          body: ListView(children: items.map(_createList).toList()));
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

  Widget _createList(String content) => Builder(
      // use Builder here in order to show the snakbar
      builder: (context) => Listener(
          child: Dismissible(
              key: Key(content),
              onDismissed: (direction) {
                setState(() {
                  var index = items.indexOf(content);
                  items.removeAt(index);
                });
                ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('$content gelöscht')));
              },
              background: Container(color: Colors.red),
              child: _listElement(content)),
            /*GestureDetector(
              onLongPress: () {
                popupDialog = createPopupDialog(content);
                Overlay.of(context)!.insert(popupDialog);
              },
              // remove the OverlayEntry from Overlay, so it would be hidden
              onLongPressEnd: (details) => popupDialog.remove(),

              onTap: () => ScaffoldMessenger.of(context)
                  .showSnackBar(SnackBar(content: Text('$content gelöscht'))),
              child: _listElement(content)
            )*/
      ));

  Offset getTapPosition(TapDownDetails details) {
    final RenderBox referenceBox = context.findRenderObject() as RenderBox;
    return referenceBox.globalToLocal(details.globalPosition);
  }

  Widget _listElement(String content) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(16.0),
            child: Text(content),
          )),
    );
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
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;
    return OverlayEntry(
        builder: (context) => Center(
              child: ClipRRect(
                  borderRadius: BorderRadius.circular(16.0),
                  child: Container(
                      padding: const EdgeInsets.all(10.0),
                      color: Colors.grey,
                      child: Text(item, style: textTheme.headline4))),
            ));
  }
}
