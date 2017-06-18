package com.hackathon.pow_wow;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.socialview.commons.Hashtag;
import com.hendraanggrian.socialview.commons.HashtagAdapter;
import com.hendraanggrian.socialview.commons.Mention;
import com.hendraanggrian.socialview.commons.MentionAdapter;
import com.hendraanggrian.widget.SocialAutoCompleteTextView;

import static java.security.AccessController.getContext;


public class DialogBoxFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_tweet, null))
                // Add action buttons
                .setPositiveButton("POST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void configureHastTagView() {

        SocialAutoCompleteTextView<Hashtag, Mention> textView = (SocialAutoCompleteTextView) getView().findViewById(R.id.tweet);
        textView.setHashtagAdapter(new HashtagAdapter(getActivity())); // or use custom adapter
        textView.setMentionAdapter(new MentionAdapter(getActivity())); // or use custom adapter

        textView.getHashtagAdapter().add(new Hashtag("follow"));
        textView.getHashtagAdapter().add(new Hashtag("followme"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
