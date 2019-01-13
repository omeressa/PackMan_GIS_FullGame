package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{
	
	/**
	 * the radios of earth
	 */
	private static final long radios = 6371000;
	/**
	 * PI
	 */
	private static final double PI =Math.PI; 
	
	
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
    /*
    * this is a reference from which we thought of the code down:
    * https://stackoverflow.com/questions/53411266/gps-to-cartesian-x-y-z
    */
		double ln = Math.cos((gps.x()*Math.PI)/180);
		double x = gps.x() + Math.toDegrees(Math.asin(local_vector_in_meter.x() / radios));
		double y = gps.y() + Math.toDegrees(Math.asin(local_vector_in_meter.y() / (radios*ln) ));
		double z = gps.z() + local_vector_in_meter.z();
		Point3D ans = new Point3D(x,y,z);
		
		return ans;
	}
	
	/** computes the 3D distance (in meters) between the two gps like points */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {


		Point3D gps0Change = gps0.ConvertToCartesian();
		Point3D gps1Change = gps1.ConvertToCartesian();
		
		return Math.abs(gps0Change.distance3D(gps1Change));

	}
	
	/** computes the 3D vector (in meters) between two gps like points */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		
		Point3D gps0ToMeters = gps0.ConvertToCartesian();
		Point3D gps1ToMeters = gps1.ConvertToCartesian();
		
		double m_x = gps1ToMeters.x()-gps0ToMeters.x();
		double m_y = gps1ToMeters.y()-gps0ToMeters.y();
		double m_z = gps1ToMeters.z()-gps0ToMeters.z();
		
		Point3D ans = new Point3D(m_x,m_y,m_z); 

		return ans.ConvertToGps();
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] ans = new double[3];
		ans[0] = gps1.north_angle(gps0);
		ans[1] = Math.toDegrees(Math.asin((gps0.z()-gps1.z())/(distance3d(gps0 , gps1))));
		ans[2] = distance3d(gps1, gps0);

		return ans;
	}
	
    public static double[] azimuth_elevation_dist2(Point3D gps0, Point3D gps1)
    {
        // Building array [YAW, PITCH, DISTANCE]
        double[] yaw_pitch_dist = new double[3];
        // Calculating the yaw:
        double lat0_radian = Math.toRadians(gps0.x());
        double lat1_radian = Math.toRadians(gps1.x());
        double diff_lon_radian = Math.toRadians((gps1.y() - gps0.y()));
        double num = Math.sin(diff_lon_radian) * Math.cos(lat1_radian);
        double den =
                (Math.cos(lat0_radian) * Math.sin(lat1_radian)) - (Math.sin(lat0_radian) * Math.cos(lat1_radian) * Math.cos(diff_lon_radian));
        double bearing_radian = Math.atan2(num, den);
        double bearing_degree = Math.toDegrees(bearing_radian);
        yaw_pitch_dist[0] = (bearing_degree + 360) % 360;
        // Calculating the distance:
        yaw_pitch_dist[2] = distance3d2(gps0, gps1);
        // Calculating the pitch:
        yaw_pitch_dist[1] = Math.toDegrees(Math.asin((gps1.z() - gps0.z()) / yaw_pitch_dist[2]));
        return yaw_pitch_dist;
    }
	public static double distance3d2(Point3D gps0, Point3D gps1) {

		Point3D p= vector3D2(gps0,gps1);
		return Math.sqrt(p.x()*p.x()+ p.y()*p.y());
	}
	public static Point3D vector3D2(Point3D gps0, Point3D gps1) {
		
		double ln = Math.cos(Math.toRadians(gps0.x()));
		double x= radios*Math.sin(Math.toRadians((gps1.x()-gps0.x())));
		double y= ln*radios*Math.sin(Math.toRadians((gps1.y()-gps0.y())));
		double z= gps1.z()-gps0.z();
		Point3D ans = new Point3D(x,y,z); 
		return ans;
	}
	
	////////////
	public double myDir(Point3D gps0, Point3D gps1) {
		double[] ans = new double[3];
		ans[0] = gps1.north_angle(gps0);
		double resulte =covertDeg(ans[0]);
		return resulte;
	}
	
	public double covertDeg(double num) {
		if( num >= 0 && num <=90 ) return 90-num;
		else return 450-num;
	}
	////////////////
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if((p.x() > -90 && p.x() < 90) && (p.y() > -180 && p.y() < 180) && (p.z() > -450)) {
			return true;
		}
		return false;
	}


}