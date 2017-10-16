package com.citch.one.auth;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.citch.one.MainActivity;
import com.citch.one.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Fragment implements AuthContract.View {

    private AuthContract.UserActionsListener mActionsListener;

    private Button mFacebookLoginButton;
    private ProgressBar mProgressBar;

    public AuthFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionsListener = new AuthPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth, container, false);

        mFacebookLoginButton = view.findViewById(R.id.facebook_login_button);

        mFacebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionsListener.initiateFacebookAuth();
            }
        });

        TextView textView = view.findViewById(R.id.terms_text);
        String text = textView.getText().toString();

        SpannableString ss = new SpannableString(text);
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(getContext(), "Terms link to be implemented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.blue));
            }
        };

        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(getContext(), "Privacy Policy link to be implemented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.blue));
            }
        };

        ss.setSpan(span1, text.indexOf("Terms"),
                text.indexOf("Terms") + "Terms".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), text.indexOf("Terms"),
                text.indexOf("Terms") + "Terms".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(span2, text.indexOf("Privacy Policy"),
                text.indexOf("Privacy Policy") + "Privacy Policy".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), text.indexOf("Privacy Policy"),
                text.indexOf("Privacy Policy") + "Privacy Policy".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        mProgressBar = view.findViewById(R.id.progress_bar);

        return view;
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayProgress(boolean isDisplay) {
        if (isDisplay) {
            mProgressBar.setVisibility(View.VISIBLE);
            mFacebookLoginButton.setEnabled(false);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mFacebookLoginButton.setEnabled(true);
        }
    }

    @Override
    public void onAuthSuccess() {
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mActionsListener.onFacebookAuthActivityResult(requestCode, resultCode, data);
    }
}
