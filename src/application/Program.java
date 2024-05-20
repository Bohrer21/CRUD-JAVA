package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoInstatiate;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoInstatiate.createSellerDao();
		
		
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Informe o número da operação que deseja realizar");
		System.out.println("1. Inserir"
				+ "\n2. Atualizar"
				+ "\n3. Deletar"
				+ "\n4. Pesquisar");
		int operation = sc.nextInt();
		
		System.out.println("Gostaria de realizar essa operação para Vendedor ou Departamento?");
		String table = sc.next();
		
		if(table.equals("Vendedor")) {
			
			switch(operation) {
			
			case 1:
				System.out.println("Insira os dados!");
				
				System.out.print("Nome do vendedor: ");
				String name = sc.next();
				
				System.out.print("Email: ");
				String email = sc.next();
				
				System.out.print("Data de Aniversário: ");
				String date = sc.next();
				
				System.out.print("Salário base: ");
				double baseSalary = sc.nextDouble();
				
				System.out.print("Qual o número do departamento dele? ");
				int departmentId = sc.nextInt();
				
				Department newDepartment = new Department(departmentId, null);
				Date birthDate = sellerDao.convertDate(date);
				Seller newSeller = new Seller(0, name, email, birthDate, baseSalary, newDepartment);
				sellerDao.insert(newSeller);
				System.out.println("Inserido id= " + newSeller.getId());
			break;
			
			case 2:
				System.out.println("Altere apenas os campos que deseja!");
				
				System.out.println("Qual o ID do vendedor?");
				int id = sc.nextInt();
				
				Seller seller = sellerDao.findById(id);
				
				System.out.print("Nome do vendedor: ");
				seller.setName(name = sc.next());
				
				System.out.print("Email: ");
				seller.setEmail(email = sc.next());
				
				System.out.print("Data de Aniversário: ");
				date = sc.next();
				
				System.out.print("Salário base: ");
				seller.setBaseSalary(baseSalary = sc.nextDouble());

				System.out.print("Qual o número do departamento dele? ");
				departmentId = sc.nextInt();
				
				birthDate = sellerDao.convertDate(date);
				seller.setBirthDate(birthDate);
				newDepartment = new Department(departmentId, null);
				seller.setDepartment(newDepartment);
				sellerDao.update(seller);
				System.out.println("Update completed");
			break;
			
			case 3:
				System.out.println("Delete o ID que precisa!");
				
				System.out.print("ID: ");
				id = sc.nextInt();
				
				seller = sellerDao.findById(id);
				System.out.println(seller);
				
				sellerDao.deleteById(id);
				System.out.println("Deletado com sucesso!");
			break;
			
			case 4:
				System.out.println("Pelo o que você deseja procurar?");
				System.out.println("1. Procurar vendedor por ID"
						+ "\n2. Procurar vendedor por Departamento"
						+ "\n3. Procurar todos os vendedores");
				int search = sc.nextInt();
				
				switch(search) {
				
				case 1:
					System.out.println("Procurando por id!");
					
					System.out.print("Qual o ID do vendedor?");
					id = sc.nextInt();
					
					seller = sellerDao.findById(id);
					System.out.println(seller);
				break;
				
				case 2:
					System.out.println("Procurando vendedor por departamento!");
					
					System.out.print("Insira o ID do departamento: ");
					id = sc.nextInt();
					
					newDepartment = new Department(id, null);
					List<Seller> list = sellerDao.findByDepartment(newDepartment);
					
					for(Seller obj : list) {
						System.out.println(obj);
					}
				break;
				
				case 3:
					System.out.println("Procurando por todos os vendedores!");
					
					list = sellerDao.findAll();
					
					for(Seller obj : list) {
						System.out.println(obj);
					}
				break;	
				}
			}
		}
		
		else if(table.equals("Departamento")) {
			
		}
		
		else {
			System.out.println("Algo de errado aconteceu!");
		}
		
		
		sc.close();
	}
}
