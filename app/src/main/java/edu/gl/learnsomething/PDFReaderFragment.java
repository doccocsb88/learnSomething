package edu.gl.learnsomething;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class PDFReaderFragment extends Fragment {


    public PDFReaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdfreader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CopyReadPDFFromAssets(getContext(),"info.pdf");
    }

    private void CopyReadPDFFromAssets(Context context,String filename)
    {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(context.getFilesDir(), filename);
        try
        {
            in = assetManager.open(filename);
            out = context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyPdfFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("exception", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + context.getFilesDir() + "/"+filename),
                "application/pdf");

        startActivity(intent);
    }

    private void copyPdfFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }
}
