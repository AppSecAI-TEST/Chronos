package pupthesis.chronos.Adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import pupthesis.chronos.Access.DataBaseHandler;
import pupthesis.chronos.R;

public class LineAdapter extends ArrayAdapter<String> {
    private String[] days;
    private String[] linename;
    private String[] status;
    private String[] id;
    private Activity context;
    int maxposition=0;
    DataBaseHandler da;
    public LineAdapter(Activity context,  String[] linename,String[] status,String id[]) {
        super(context, R.layout.activity_line_adapter, linename);
        this.context = context;
        this.linename = linename;
        this.status = status;
        this.id=id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_line_adapter, null, true);
        TextView DaysTV,StatusTV,LinenameTV;
        CardView cardDays,cardLinename,cardStatus;
        DaysTV=(TextView)listViewItem.findViewById(R.id.DaysTV);
        StatusTV=(TextView)listViewItem.findViewById(R.id.StatusTV);
        LinenameTV=(TextView)listViewItem.findViewById(R.id.LinenameTV);


        cardDays=(CardView)listViewItem.findViewById(R.id.cardDays);
        cardLinename=(CardView)listViewItem.findViewById(R.id.cardLinename);
        cardStatus=(CardView)listViewItem.findViewById(R.id.cardStatus);
        da=new DataBaseHandler(context);
        int countdays=da.getCountlineTask(id[position]);
        DaysTV.setText(countdays+" Day/s");
        LinenameTV.setText(linename[position]);
        StatusTV.setText(status[position]);
        if (position != maxposition) {
            AnimationSet set = new AnimationSet(true);

            Animation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(400);
            set.addAnimation(animation);

            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            animation.setDuration(500);
            set.addAnimation(animation);

            listViewItem.startAnimation(set);
            maxposition = position;
        }

        return  listViewItem;
    }
}
