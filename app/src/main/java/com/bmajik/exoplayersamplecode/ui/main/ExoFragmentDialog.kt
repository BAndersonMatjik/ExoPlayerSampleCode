package com.bmajik.exoplayersamplecode.ui.main

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.bmajik.exoplayersamplecode.R
import com.bmajik.exoplayersamplecode.databinding.ExoFragmentDialogBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener

class ExoFragmentDialog : DialogFragment() {

    lateinit var binding: ExoFragmentDialogBinding
    private val player: SimpleExoPlayer by lazy {
        SimpleExoPlayer.Builder(requireContext()).build()
    }
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return AlertDialog.Builder(requireContext())
//            .create()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.exo_fragment_dialog, container, false)
        Log.d("ExoFragmentDialog", "onCreateView: init ${binding.videoView.visibility}")
        bindingView()

        return binding.root
    }

    private fun bindingView() {
        binding.apply {
            videoView.player = player
            videoView.useController = false
            videoView.setKeepContentOnPlayerReset(true)
            initPlayer()
        }
    }

    override fun onStart() {
        super.onStart()
//        if (Util.SDK_INT >= 24) {
            initPlayer()
//        }
    }

    private fun initPlayer() {
//        val firstVideoUri = Uri.parse("asset:///localfile.mp3")
        val mediaItem = MediaItem.fromUri("https://r3---sn-npoeenl7.googlevideo.com/videoplayback?expire=1619666971&ei=utOJYLD1OcGjx_AP4NSZsAU&ip=45.153.33.166&id=o-ACErwMUJOOu9urXWsYfjqMBk8wvmaHWQ5qaUX7EtUtue&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=gadJpqL0dy_ArKPAPnsFw-QF&gir=yes&clen=18343878&ratebypass=yes&dur=209.142&lmt=1602466112083020&fvip=3&fexp=24001373,24007246&c=WEB&txp=5316222&n=ejHAGWM8nPRNon5&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAPGF3ECqd1pD9KYPjnVF0eZa_TkO2t_PPHUHbVXZEehBAiAZCGzsCXv5cDAFr54kHmnWOZDIOELR8IFcYMLq9ks5tQ%3D%3D&rm=sn-5hnes77z&req_id=8ed2029c16ffa3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-jcopn2-jb3l7s&cms_redirect=yes&mh=X_&mip=101.128.127.164&mm=29&mn=sn-npoeenl7&ms=rdu&mt=1619645320&mv=m&mvi=3&pl=24&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhAK6aR9q695W4sa_BaYFFsIlKMIAUJkCsc4pHkT6gGSCvAiBmov9qa5sKPgBQYG-co2mrk9qYxR-sSWK1mywQFvbxKA%3D%3D")
        player.setMediaItem(mediaItem)
        player.playWhenReady = playWhenReady
        player.prepare();
    }

    private fun releasePlayer() {
        playWhenReady = player.playWhenReady;
        playbackPosition = player.currentPosition;
        currentWindow = player.currentWindowIndex;
        player.release();
    }

    override fun onPause() {
        super.onPause()
//        if (Util.SDK_INT < 24) {
            releasePlayer();
//        }
    }

    override fun onStop() {
        super.onStop()
//        if (Util.SDK_INT >= 24) {
            releasePlayer();
//        }
    }
}