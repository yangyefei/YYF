package common.utils;

public enum UrlsOfPre {


	/**
	 * 后台管理系统url
	 * @author ALLen
	 *
	 */
	BackGroundSystem {
		@Override
		public String getUrl(){
			String url = "http://trip.com";			
			return url;			
		}
	};
	
	public abstract String getUrl();

}

