import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:video_player/video_player.dart';

class Multimedia extends StatefulWidget {
  const Multimedia({super.key});

  @override
  State<StatefulWidget> createState() => _MultimediaState();
}

class _MultimediaState extends State<Multimedia> {
  AudioPlayer player = AudioPlayer();
  bool isPlaying = false;
  bool audioPlayed = false;
  late Uint8List audioBytes;

  // Video Player
  late VideoPlayerController _controller;
  late Future<void> _initializeVideoPlayerFuture;

  @override
  void initState() {
    super.initState();
    String audioAsset = "assets/sampleaudio.mp3";

    Future.delayed(Duration.zero, () async {
      ByteData bytes = await rootBundle.load(audioAsset);
      audioBytes =
          bytes.buffer.asUint8List(bytes.offsetInBytes, bytes.lengthInBytes);
    });

    _controller = VideoPlayerController.network(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

    // Initialize the controller and store the Future for later use.
    _initializeVideoPlayerFuture = _controller.initialize();

    // Use the controller to loop the video.
    _controller.setLooping(true);
  }

  @override
  void dispose() {
    // Ensure disposing of the VideoPlayerController to free up resources.
    _controller.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return Scaffold(
      body: Column(children: [
        Padding(
          padding: const EdgeInsets.fromLTRB(0, 50, 0, 0),
          child: Text("Audio", style: textTheme.headline6),
        ),
        Row(
          children: [
            IconButton(
                onPressed: () async {
                  if (!isPlaying && !audioPlayed) {
                    int result = await player.playBytes(audioBytes);
                    if (result == 1) {
                      setState(() {
                        isPlaying = true;
                        audioPlayed = true;
                      });
                    } else {
                      const snackBar =
                          SnackBar(content: Text("Error while playing audio."));
                      ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    }
                  } else if (!isPlaying && audioPlayed) {
                    int result = await player.resume();
                    if (result == 1) {
                      setState(() {
                        isPlaying = true;
                        audioPlayed = true;
                      });
                    } else {
                      const snackBar = SnackBar(
                          content: Text("Error while resuming audio."));
                      ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    }
                  }
                },
                icon: const Icon(Icons.play_arrow)),
            IconButton(
                onPressed: () async {
                  if (isPlaying) {
                    int result = await player.pause();
                    if (result == 1) {
                      //pause success
                      setState(() {
                        isPlaying = false;
                      });
                    } else {
                      const snackBar =
                          SnackBar(content: Text("Error while pausing audio."));
                      ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    }
                  }
                },
                icon: const Icon(Icons.pause))
          ],
        ),
        Text("Video", style: textTheme.headline6),
        FutureBuilder(
          future: _initializeVideoPlayerFuture,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.done) {
              // If the VideoPlayerController has finished initialization, use
              // the data it provides to limit the aspect ratio of the video.
              return AspectRatio(
                aspectRatio: _controller.value.aspectRatio,
                // Use the VideoPlayer widget to display the video.
                child: VideoPlayer(_controller),
              );
            } else {
              // If the VideoPlayerController is still initializing, show a
              // loading spinner.
              return const Center(
                child: CircularProgressIndicator(),
              );
            }
          },
        ),
        Row(
          children: [
            IconButton(
                onPressed: () {
                  setState(() {
                    _controller.play();
                  });
                },
                icon: const Icon(Icons.play_arrow)),
            IconButton(
                onPressed: () {
                  setState(() {
                    _controller.pause();
                  });
                },
                icon: const Icon(Icons.pause))
          ],
        )
      ]),
    );
  }
}
