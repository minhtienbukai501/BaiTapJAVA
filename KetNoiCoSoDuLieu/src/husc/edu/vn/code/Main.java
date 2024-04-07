package husc.edu.vn.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import husc.edu.vn.model.Database;
import husc.edu.vn.model.Hang;

public class Main {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// CSDL như thế nào thì tạo database như thế
	
	//đọc file nào
	public static Scanner scanInput = new Scanner(System.in);
	public static Connection con = Database.getConnect();
	public static ArrayList<Hang> layDanhSachHang() {
		ArrayList<Hang> listHang = new ArrayList<Hang>();
		try {
			Scanner scan = new Scanner(new File("hang.txt"));
			while(scan.hasNext()) {
				String Line = scan.nextLine();
				
				String params[] = Line.split(",");
				String maHang = params[0];
				String tenHang = params[1];
				String ngayNhapHang = params[2];
				String soLuongString =  params[3];
				int soLuong = Integer.parseInt(soLuongString);
				double gia = Double.parseDouble(params[4]);
				try {
					Hang hang = new Hang(maHang, tenHang, sdf.parse(ngayNhapHang), soLuong, gia);
					listHang.add(hang);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return listHang;
	}
	
	
	public static boolean trungTenorMa(ArrayList<Hang> listHang, String ma, String ten, int index) {
		int len  = listHang.size();
		for(int i = 0; i < len; i++) {
			if((listHang.get(i).getMaHang().compareToIgnoreCase(ma)== 0  || listHang.get(i).getTenHang().compareToIgnoreCase(ten)== 0) && i != index) {
				return true;
			}
		}
		
		
		return false;
	}
	
	public static void themHang(ArrayList<Hang> listHang) {
		Hang hang;
		hang = new Hang();
		hang.nhap();
		boolean checkTrungMaorTen = trungTenorMa(listHang, hang.getMaHang(), hang.getTenHang(), -1);
		if(checkTrungMaorTen) {
			System.out.println("Ten hoac ma da ton tai trong danh sach");
			return;
		}
		listHang.add(hang);
	}
	
	
	public static void xoaHang(ArrayList<Hang> listHang, String maHang) {
		int len = listHang.size();
		for(int i = 0; i < len; i++) {
			if(listHang.get(i).getMaHang() ==  maHang) {
				listHang.remove(i);
				break;
			}
		}
	}
	
	public static void suaHang(ArrayList<Hang> listHang) {
		System.out.println("Nhap  ten can sua: ");
		Hang hang = new Hang();
		String tenHang = scanInput.nextLine();
		int len = listHang.size();
		for(int i = 0; i < len; i++) {
			if(listHang.get(i).getTenHang().compareToIgnoreCase(tenHang) == 0) {
				hang.setTenHang(tenHang);
				hang.sua();
				if(trungTenorMa(listHang, hang.getMaHang(), "", i) == true) {
					System.out.println("Ma da ton tai trong danh sach");
					return;
				}
				
				listHang.set(i, hang);
				System.out.println("sua thanh cong");
				return;
			}
		}
		
		System.out.println("khong tim thay ten can sua!");
	}
	
	
	public static void xoaHang(ArrayList<Hang> listHang) {
		System.out.println("Nhap  ten can xoa: ");
		String tenHang = scanInput.nextLine();
		
		int len = listHang.size();
		for(int i = 0; i < len; i++) {
			if(listHang.get(i).getTenHang().compareToIgnoreCase(tenHang) == 0) {
				listHang.remove(i);
				System.out.println("Xoa Thanh Cong!");
				return;
			}
		}
		
		System.out.println("Ten hang khong ton tai!");
	}
	
	public static void timKiem(ArrayList<Hang> listHang) {
		System.out.println("Nhap  ten can tim kiem: ");
		String tenHang = scanInput.nextLine();
		int len = listHang.size();
		for(int i = 0; i < len; i++) {
			if(listHang.get(i).getTenHang().compareToIgnoreCase(tenHang) == 0) {
				System.out.println(listHang.get(i));
				System.out.println("Tim Thay!");
				return;
			}
		}
		
		System.out.println("khong tim thay");
	}


	
	public static boolean checkMaHang(Connection con, String maHang) {
		String sql = "select * from Hang where MaHang =?";
		
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setNString(1, maHang);
			
			ResultSet rs = pre.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void luuThongTinHangVaoCSDL(ArrayList<Hang> listHang) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		if(con != null) {
			for(Hang hang : listHang) {
				if(!checkMaHang(con, hang.getMaHang())) {
					String sql = "insert into Hang values('" + hang.getMaHang()  +"', N'"+hang.getTenHang()+"' , '"+ sdf2.format(hang.getNgayNhapHang())+"', " + hang.getSoLuong()+", "+hang.getGia()+ ")";
					try {
						PreparedStatement pre = con.prepareStatement(sql);
						pre.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
				
			}
			
		}
		
		
	}
	
	public static void hienThiThongTin(ArrayList<Hang> listHang) {
		for(Hang x: listHang) {
			System.out.println(x);
		}
	}

	public static void main(String[] args) {
		int LuaChon;
		ArrayList<Hang> dsHang = new ArrayList<Hang>();
		do {
			System.out.println("===============MENU===============\n");
			System.out.println("1.Lay danh sach tu file va Luu danh sach vao database");
			System.out.println("2.Them Hang");
			System.out.println("3.Xoa Hang");
			System.out.println("4.Sua Hang");
			System.out.println("5.Tim Kiem Hang");
			System.out.println("6.Hien thi danh sach");
			System.out.println("0.Thoat");
			System.out.println("===============End===============");
			System.out.println("Ban chon: ");
			LuaChon = Integer.parseInt(scanInput.nextLine());
		
			if(LuaChon == 1) {
				dsHang = layDanhSachHang();
				luuThongTinHangVaoCSDL(dsHang);	
			} else if(LuaChon == 2) {
				themHang(dsHang);
			} else if(LuaChon == 3) {
				xoaHang(dsHang);
			} else if(LuaChon == 4) {
				suaHang(dsHang);
			} else if(LuaChon == 5) {
				timKiem(dsHang);
			} else if(LuaChon == 6) {
				hienThiThongTin(dsHang);
			}
			
		} while(LuaChon != 0);
	}

}
