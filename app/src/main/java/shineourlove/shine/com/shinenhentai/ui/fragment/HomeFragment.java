package shineourlove.shine.com.shinenhentai.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import shineourlove.shine.com.shinenhentai.R;
import shineourlove.shine.com.shinenhentai.api.DataPostListener;
import shineourlove.shine.com.shinenhentai.api.presenter.BaseGetPresenter;
import shineourlove.shine.com.shinenhentai.ui.BaseFragment;
import shineourlove.shine.com.shinenhentai.utils.ShineUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements DataPostListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    BaseGetPresenter baseGetPresenter;
    int page;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void init() {
//        bookApi.getBook("https://nhentai.net/");
        baseGetPresenter = new BaseGetPresenter(this);
        baseGetPresenter.getData("?page=" + page);
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
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

    @Override
    public void onSuccess(Document document) {

        Elements scripts = document.getElementsByTag("script");
        Elements gallery = document.getElementsByClass("gallery");

        if (gallery.first() != null) {
            ShineUtils.logDebug("data_log", "first: " + gallery.first().html());
            String tags = gallery.first().attr("data-tags").replace(' ', ',');
            ShineUtils.logDebug("data_log", "tags: " + tags);
        }

        if (scripts.last() == null) {
            return;
        }
        String str = scripts.last().html();
        int s = str.indexOf("new N.gallery(") + 14, s1 = str.indexOf('\n', s) - 2;
        if (s == 13 || s1 < 0) return;
        str = str.substring(s, s1);
    }

    @Override
    public void onError(String e) {

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
