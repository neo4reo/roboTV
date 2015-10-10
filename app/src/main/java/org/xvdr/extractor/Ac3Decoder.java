package org.xvdr.extractor;

import android.util.Log;

import com.google.android.exoplayer.C;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableByteArray;

import org.xvdr.audio.AC3DecoderNative;
import org.xvdr.robotv.tv.StreamBundle;

final class Ac3Decoder extends ElementaryStreamReader {

    final static String TAG = "Ac3Decoder";

    boolean hasOutputFormat = false;

    StreamBundle.Stream mStream;
    AC3DecoderNative mDecoder;

    public Ac3Decoder(TrackOutput output, StreamBundle.Stream stream) {
        super(output);
        mDecoder = new AC3DecoderNative(AC3DecoderNative.layoutStereo);
        mStream = stream;
    }

    @Override
    public void consume(ParsableByteArray data, long pesTimeUs, boolean isKeyframe, long durationUs) {
        int decodedLength = mDecoder.decode(data.data, 0, data.capacity());
        if(decodedLength == 0) {
            Log.e(TAG, "Unable to decode frame data");
            return;
        }

        int channels = mDecoder.getChannels();
        int sampleRate = mDecoder.getSampleRate();

        ParsableByteArray audioChunk = new ParsableByteArray(decodedLength);

        if(!mDecoder.getOutput(audioChunk.data, 0, audioChunk.capacity())) {
            Log.e(TAG, "failed to get output buffer");
            return;
        }

        if(!hasOutputFormat) {
            MediaFormat format = MediaFormat.createAudioFormat(
                    mStream.physicalId, // < trackId
                    MimeTypes.AUDIO_RAW,
                    mStream.bitRate,
                    MediaFormat.NO_VALUE,
                    durationUs,
                    channels,
                    sampleRate,
                    null,
                    mStream.language);
            output.format(format);
            hasOutputFormat = true;
        }

        output.sampleData(audioChunk, audioChunk.capacity());
        output.sampleMetadata(pesTimeUs, C.SAMPLE_FLAG_SYNC, audioChunk.capacity(), 0, null);
    }
    
    @Override
    public void packetFinished() {
    }

    @Override
    public void seek() {
    }

}
