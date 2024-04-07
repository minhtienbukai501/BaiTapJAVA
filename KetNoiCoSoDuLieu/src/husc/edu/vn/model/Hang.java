package husc.edu.vn.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Hang {
	private String maHang, tenHang;
	private Date ngayNhapHang;
	private int soLuong;
	private double gia;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void nhap() {
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nhap ma hang: ");
		maHang = scan.nextLine();
		
		
	
		
		System.out.println("Nhap ten hang: ");
		tenHang = scan.nextLine();
		System.out.println("Ngay nhap hang(dd/MM/yyyy): ");
		String ngay = scan.nextLine();
		try {
			ngayNhapHang = sdf.parse(ngay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Nhap so luong: ");
		soLuong = Integer.parseInt(scan.nextLine());
		
		System.out.println("Nhap gia: ");
		gia = Double.parseDouble(scan.nextLine());
		
	}
	
public void sua() {
		
		
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Nhap ma hang: ");
		maHang = scan.nextLine();
		
		System.out.println("Ngay nhap hang(dd/MM/yyyy): ");
		String ngay = scan.nextLine();
		try {
			ngayNhapHang = sdf.parse(ngay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Nhap so luong: ");
		soLuong = Integer.parseInt(scan.nextLine());
		
		System.out.println("Nhap gia: ");
		gia = Double.parseDouble(scan.nextLine());
		
	}
	
	public Hang() {
		
	}
	
	public String getMaHang() {
		return maHang;
	}
	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	public Date getNgayNhapHang() {
		return ngayNhapHang;
	}
	public void setNgayNhapHang(Date ngayNhapHang) {
		this.ngayNhapHang = ngayNhapHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public Hang(String maHang, String tenHang, Date ngayNhapHang, int soLuong, double gia) {
		super();
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.ngayNhapHang = ngayNhapHang;
		this.soLuong = soLuong;
		this.gia = gia;
	}
	@Override
	public String toString() {
		
		return this.maHang + "\t" + this.tenHang + "\t" + sdf.format(ngayNhapHang)  + "\t" + this.soLuong + "\t" + this.gia;
	}
	
	
	
}
