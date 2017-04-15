package taha.com.hearit.Frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import taha.com.hearit.Activities.PostDetails;
import taha.com.hearit.Entity.Comment;
import taha.com.hearit.R;

import static taha.com.hearit.Activities.Main.myPROFILE;
import static taha.com.hearit.Activities.Main.user;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addComment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addComment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addComment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private DatabaseReference mDatabase;
    private EditText corpse;
    private Button add;

    public addComment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addComment.
     */
    // TODO: Rename and change types and number of parameters
    public static addComment newInstance(String param1, String param2) {
        addComment fragment = new addComment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_comment, container, false);
        corpse = (EditText)v.findViewById(R.id.message);
        add = (Button)v.findViewById(R.id.cnfrmButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentSth(corpse.getText().toString());
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    private void commentSth(String text) {
        String key = mDatabase.child("comments").push().getKey();
        Comment comment = new Comment(myPROFILE.getName(), text, myPROFILE.getUps(), PostDetails.post.getKey());

        Map<String, Object> commentValues = comment.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/comments/" + PostDetails.post.getKey() +"/"+key, commentValues);
        childUpdates.put("/user-comments/" + user.getUid() + "/" +PostDetails.post.getKey() +"/"+key, commentValues);
        mDatabase.updateChildren(childUpdates);
    }
}
