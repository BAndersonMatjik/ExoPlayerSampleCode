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
        val mediaItem = MediaItem.fromUri("https://r3---sn-npoe7ner.googlevideo.com/videoplayback?expire=1619373714&ei=MlqFYKezHZie4ALj_464BQ&ip=42.119.139.254&id=o-AHqa6fuokYmcDcmTKURehdq5nKgEsHiQufWQxeLyyLwy&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=o27nlcbVM9w22vz_6dzGD9oF&gir=yes&clen=18343878&ratebypass=yes&dur=209.142&lmt=1602466112083020&fvip=3&fexp=23886219,24001373,24007246&beids=23886219&c=WEB&txp=5316222&n=mUPEXDWYy7Oy9v1Swb&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgVWKTZLv-Rae1e31ZN8-aAmbo8yoUpuoirXovth1o6CACIQDF35z-d5BlR-wDhRNDbsA2bSzJw_C2iL1coPoRGxUFNg%3D%3D&rm=sn-42u-nbos67s,sn-nposy7s&req_id=e76a0ce4df43a3ee&redirect_counter=2&cms_redirect=yes&ipbypass=yes&mh=X_&mip=101.128.127.157&mm=30&mn=sn-npoe7ner&ms=nxu&mt=1619352739&mv=m&mvi=3&pl=24&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhALWoxLmRFImdGJsDTbUzV5msEaKRE_vXslJAkgr9e4SxAiAeYPMcxNKecS5YINKQF4TRrWLdjjKqBaUW-JpyoYQ-9A%3D%3D")
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