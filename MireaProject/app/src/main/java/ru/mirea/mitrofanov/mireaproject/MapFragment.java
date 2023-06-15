package ru.mirea.mitrofanov.mireaproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


import ru.mirea.mitrofanov.mireaproject.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private Context mContext;

    private MapView mapView;

    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        mContext = inflater.getContext();
//        textView = binding.text1;
        mapView = binding.mapView;
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(new
                GpsMyLocationProvider(mContext), mapView);
        locationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(locationNewOverlay);
//Compas
        CompassOverlay compassOverlay = new CompassOverlay(mContext, new
                InternalCompassOrientationProvider(mContext), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        final DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);

        Marker stadium = new Marker(mapView);
        stadium.setPosition(new GeoPoint(55.7158, 37.5537));
        stadium.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(mContext.getApplicationContext(),"Стадион 'Лужники'",
                        Toast.LENGTH_SHORT).show();
                binding.infoText.setText("Стадион «Лужники́» — московский стадион, центральная часть спортивного комплекса «Лужники», расположенного неподалёку от Воробьёвых гор. Построен в рекордные сроки — за 450 дней (1 год и 85 дней). Был открыт 31 июля 1956 года товарищеским футбольным матчем между сборными РСФСР и КНР. Летом 1956-го спортивный комплекс принял I Спартакиаду народов СССР, а через год — VI Всемирный фестиваль молодёжи и студентов. Летом 1980 г. арена стадиона стала ареной открытия XXII летних Олимпийских игр. После реконструкции, завершившейся в 2017 году, вмещает 81 тысячу человек и является самым большим стадионом России.");
                return true;
            }
        });
        mapView.getOverlays().add(stadium);
        stadium.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.stadium, null));
        stadium.setTitle("Стадион 'Лужники'");

        Marker restaurant = new Marker(mapView);
        restaurant.setPosition(new GeoPoint(55.7571472, 37.6020909));
        restaurant.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(mContext.getApplicationContext(),"Прекраснейший ресторан японской кухни Jun",
                        Toast.LENGTH_SHORT).show();
                binding.infoText.setText("Новый ресторан холдинга Lucky Group и еще одна яркая точка на Большой Никитской. Место целиком и полностью подчинено Японии — вкусы и запахи, органолептика, традиции и немосковский ритм, в который погружаешься, сидя за местной стойкой и поглядывая на медитативную разделку рыбы. Едой тут занимается Артемий Лопатин, давно и основательно посвятивший себя японской кухне: в 12 лет он научился крутить роллы в родном Кирове, а спустя десять лет — в Нью-Йорке и Лондоне.");
                return true;
            }
        });
        mapView.getOverlays().add(restaurant);
        restaurant.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.restaurant, null));
        restaurant.setTitle("Ресторан японской кухни Jun");

        Marker university = new Marker(mapView);
        university.setPosition(new GeoPoint(55.669998, 37.4801923));
        university.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(mContext.getApplicationContext(),"Наш вуз 'РТУ МИРЭА'",
                        Toast.LENGTH_SHORT).show();
                binding.infoText.setText("РТУ МИРЭА — высшее учебное заведение в Москве, которое образовано в 2015 году в результате объединения МИРЭА, МГУПИ, МИТХТ имени М. В. Ломоносова и ряда образовательных, научных, конструкторских и производственных организаций. В университете ведется подготовка по образовательным программам в сферах IT, компьютерной безопасности, электроники, радиотехники, робототехники, химии, биотехнологий и др., а также предоставляется возможность получения дополнительного образования.");
                return true;
            }
        });
        mapView.getOverlays().add(university);
        university.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.university, null));
        university.setTitle("Вуз 'РТУ МИРЭА'");

        return binding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(mContext,
                PreferenceManager.getDefaultSharedPreferences(mContext));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(mContext,

                PreferenceManager.getDefaultSharedPreferences(mContext));

        if (mapView != null) {
            mapView.onPause();
        }
    }
}