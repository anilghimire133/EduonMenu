package com.bgtechsolution.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;



import java.util.ArrayList;
public class MyExListAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<ParentPojo> ListTerbaru;


    ArrayList<ArrayList<ChildPojo>> ListChildTerbaru;

    public MyExListAdapter(Context  context, ArrayList<ParentPojo> listTerbaru, ArrayList<ArrayList<ChildPojo>> listChildTerbaru) {
        this.context = context;
        ListTerbaru = listTerbaru;
        ListChildTerbaru = listChildTerbaru;
    }
    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

//own
//    @Override
//    public int getGroupCount() {
//        return ListTerbaru.size();
//    }

    @Override
    public ChildPojo getChild(int groupPosition, int childPosition) {
        return ListChildTerbaru.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {

        ChildPojo childTerbaru = getChild(groupPosition, childPosition);
        ViewHolder holder= null;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child, null);

            holder=new ViewHolder();
            holder.begdate1=(TextView)convertView.findViewById(R.id.textChild);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.begdate1.setText(childTerbaru.getName());

        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return ListChildTerbaru.get(groupPosition).size();
    }

    @Override
    public ParentPojo getGroup(int groupPosition) {
        return ListTerbaru.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return ListTerbaru.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentPojo terbaruModel = (ParentPojo) getGroup(groupPosition);
        ViewHolder holder= null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_parent, null);

            holder=new ViewHolder();
            holder.nama=(TextView)convertView.findViewById(R.id.textParent);



//            holder.alamat=(TextView)convertView.findViewById(R.id.address);
            convertView.setTag(holder);

        }

        else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.nama.setText(terbaruModel.getCreator());

//        holder.alamat.setText(terbaruModel.getAlamat());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }


    static class ViewHolder{
        TextView begdate1, enddate1,nama, alamat, imageid;
    }

}