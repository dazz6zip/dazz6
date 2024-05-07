package pack;

import java.util.List;

import domain.SangpumTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class SangpumCRUD {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gojpa");

		// EntityManager : thread 단위로 작업
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		// 자료 추가 (persist)
//		try {
//			transaction.begin();
//			SangpumTable sangtab = new SangpumTable(5, "도시락", 3, 6000);
//			em.persist(sangtab);
//			transaction.commit();
//		} catch (Exception e) {
//			System.out.println("INSERT ERROR : " + e);
//			transaction.rollback();
//			return;
//		}

		// 자료 수정
//		try {
//			transaction.begin();
//			SangpumTable sangtab = em.find(SangpumTable.class, "5");
//			if (sangtab == null) {
//				System.out.println("해당 자료 없음");
//				transaction.rollback();
//			} else {
//				String newName = "마스크";
//				sangtab.setSang(newName);
//				transaction.commit();
//			}
//		} catch (Exception e) {
//			System.out.println("UPDATE ERROR : " + e);
//			transaction.rollback();
//			return;
//		}

		// 자료 삭제
//		try {
//			transaction.begin();
//			int findCode = 5;
//			SangpumTable sangtab = em.find(SangpumTable.class, findCode);
//			if (sangtab == null) {
//				System.out.println("해당 자료 없음");
//				transaction.rollback();
//			} else {
//				em.remove(sangtab);
//				System.out.printf("자료 삭제 : %s", findCode);
//				transaction.commit();
//			}
//		} catch (Exception e) {
//			System.out.println("DELETE ERROR : " + e);
//			transaction.rollback();
//			return;
//		}

		// JPA를 사용한 DML 처리
		try {
			System.out.println("전체 자료 읽기 1");
			// select method 사용
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SangpumTable> query = cb.createQuery(SangpumTable.class);
			Root<SangpumTable> root = query.from(SangpumTable.class); // 조회의 시작점을 의미하는 root 객체 생성
			query.select(root);
			List<SangpumTable> resultList = em.createQuery(query).getResultList();
			for (SangpumTable st : resultList) {
				System.out.println(st.getCode() + "\t" + st.getSang() + "\t" + st.getSu() + "\t" + st.getDan());
			}

			System.out.println("\n전체 자료 읽기 2");
			// TypedQuery를 이용하여 JPQL 사용
//			TypedQuery<SangpumTable> queryql = em.createQuery("SELECT s FROM SangpumTable s", SangpumTable.class);
//			List<SangpumTable> list = queryql.getResultList();
			List<SangpumTable> list = em.createQuery("SELECT s FROM SangpumTable s", SangpumTable.class)
					.getResultList(); // 위 구문과 동일
			for (SangpumTable st : list) {
				System.out.println(st.getCode() + "\t" + st.getSang() + "\t" + st.getSu() + "\t" + st.getDan());
			}

			System.out.println("\n부분 자료 읽기 1");
			int findId = 1; // type이 String이어도 상관없음 // PRIMARY KEY 칼럼이 대상
			SangpumTable sangtab = em.find(SangpumTable.class, findId);
			if (sangtab == null) {
				System.out.println("자료 없음");
			} else {
				System.out.printf("%s\t%s\t%s\t%s\n", sangtab.getCode(), sangtab.getSang(), sangtab.getSu(),
						sangtab.getDan());
			}

			System.out.println("\n부분 자료 읽기 2");
			TypedQuery<SangpumTable> typedQuery = em.createQuery(query.where(cb.equal(root.get("sang"), "장갑")));
			List<SangpumTable> resultList2 = typedQuery.getResultList();
			for (SangpumTable sangtab2 : resultList2) {
				System.out.printf("%s\t%s\t%s\t%s\n", sangtab2.getCode(), sangtab2.getSang(), sangtab2.getSu(),
						sangtab2.getDan());
			}

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		} finally {
			em.close(); // 권장
			emf.close(); // 필수
		}
	}
}
