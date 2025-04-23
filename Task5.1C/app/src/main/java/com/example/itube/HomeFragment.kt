package com.example.itube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class HomeFragment : Fragment() {
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var videoUrlEditText: EditText
    private lateinit var playButton: Button
    private lateinit var addToPlaylistButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        youtubePlayerView = view.findViewById(R.id.youtube_player_view)
        videoUrlEditText = view.findViewById(R.id.video_url_edit_text)
        playButton = view.findViewById(R.id.play_button)
        addToPlaylistButton = view.findViewById(R.id.add_to_playlist_button)

        lifecycle.addObserver(youtubePlayerView)

        playButton.setOnClickListener {
            val videoUrl = videoUrlEditText.text.toString()
            val videoId = extractVideoId(videoUrl)
            if (videoId != null) {
                youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        }

        addToPlaylistButton.setOnClickListener {
            val videoUrl = videoUrlEditText.text.toString()
            val videoId = extractVideoId(videoUrl)
            if (videoId != null) {

            }
        }

        return view
    }

    private fun extractVideoId(url: String): String? {
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
        val compiledPattern = Regex(pattern)
        return compiledPattern.find(url)?.value
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
} 