package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 예제 참고 : https://cafe.daum.net/flowlife/HqLo/72

// Stream 인터페이스 : 컬렉션, 배열 등의 저장 요소를 하나씩 참조하여 인터페이스(람다식)를 적용하며, 반복 처리를 가능하게 한다. (반복자 역할)
// 정렬, 집계, 빅데이터 처리 등도 가능하다.
// 일회용이며, 내부 반복으로 작업을 처리하고 원본 데이터를 변경하지 않는다.
public class MyStream01 {
	public MyStream01() {
		test01(); // Stream 생성
		test02(); // Collection에 Stream 적용
		test03(); // VO 클래스 사용
	}

	private void test01() {
		System.out.println("----- Test 01 -----");
		// 1) Collection에 Stream 생성
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> listStream = list.stream(); // stream 생성

		// 2) 배열에 스트림 생성
		Stream<String> stream1 = Stream.of("a", "b", "c");
		Stream<String> stream2 = Stream.of(new String[] { "a", "b", "c" });
		Stream<String> stream3 = Arrays.stream(new String[] { "a", "b", "c" });
		Stream<String> stream4 = Arrays.stream(new String[] { "a", "b", "c" }, 0, 3); // 0 이상 3 미만

		// 3) 원시(기본형 데이터) Stream 생성
		IntStream istream = IntStream.range(5, 10); // 5 이상 10 미만의 스트림 생성
//		DoubleStream...
		istream.forEach((para) -> {
			System.out.print(para + " ");
		});

		System.out.println("\n----- Test 02 -----");
	}

	private void test02() {
		List<String> list = Arrays.asList("레밍스", "팩맨", "마리오");
//		list.add("소닉"); // Arrays.asList()를 사용했기 때문에 새로운 요소 추가 및 기존 요소 삭제가 불가능함 (READ ONLY)
		Iterator<String> iter = list.iterator(); // 전통적 : 외부 반복자 사용
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
		for (String str : list) { // 전통적 : 향상된 for문 사용
			System.out.print(str + " ");
		}
		for (String str : list) { // 전통적 : 향상된 for문 사용
			System.out.print(str + " ");
		}
		System.out.println();
		Stream<String> stream = list.stream(); // 컬렉션으로부터 String 객체를 얻어 Stream 생성
		stream.forEach((str) -> {
			System.out.print(str + " ");
		});
//		stream.forEach((str) -> { // stream은 일회용이기 때문에 ERROR 발생
//			System.out.print(str + " ");
//		});
		list.stream().forEach((str) -> { // stream을 다시 만들었기 때문에 정상 실행됨
			System.out.print(str + " ");
		});
		list.stream().forEach(System.out::print); // 위 구문을 이렇게 쓸 수 있음 (str만 반복 출력)

		System.out.println("\n----------");

		// Stream을 사용하여 체이닝 작업 : 모든 필요한 작업을 단일 스트림 파이프라인*에서 처리 가능
		// 파이프라인 : 일련의 처리 단계 묶음
		// 어떤 스트림 요소들의 합을 구하는 과정에서 요소 값을 먼저 출력하고 싶은 경우

		int sum = IntStream.of(1, 3, 5, 7).peek(System.out::print).sum(); // average, count, max...
		// sum() 메소드를 쓰기 전에 출력 처리하기 위해 peek(System.out::print) 사용한 것.
		System.out.println("\n합 : " + sum);

		list.stream().peek(System.out::print).forEach(System.out::print);
		// console : 레밍스레밍스팩맨팩맨마리오마리오
		// 내부 반복이 일어나는 것

		System.out.println("\n-- 병렬 처리");
		Stream<String> streamPa = list.parallelStream(); // 병렬 스트림 객체 생성 (parallelStream : 병렬스트림)
		streamPa.forEach((str) -> System.out.print(str + " ")); // 순서 랜덤

		System.out.println("\n-- 정렬 처리");
		Stream<String> streamSort = list.stream().sorted(); // default : ascending(오름차순)
		Stream<String> streamSort2 = list.stream().sorted(Comparator.reverseOrder()); // descending(내림차순)
		streamSort.forEach(System.out::print);
		System.out.println();
		streamSort2.forEach(System.out::print);

		Stream<String> streamSort3 = list.stream().distinct().sorted(); // distinct() : 중복 제거
		System.out.println("\n----- Test 03 -----");
	}

	private void test03() {
		System.out.println("-- VO 클래스로 Collection 작성");
		List<Student> slist = Arrays.asList(new Student("레밍스", "남", 22), new Student("팩맨", "남", 25),
				new Student("마리오", "남", 28), new Student("피치", "여", 20), new Student("마라라", "여", 22));

		Stream<Student> stream = slist.stream();
		stream.forEach((p) -> {
			System.out.println(p.getName() + "\t" + p.getGender() + "\t" + p.getAge());
		});

		System.out.println("-- Collection 자료에 대한 중간 처리, 최종 처리 가능");
		// 개인의 나이를 출력하고, 최종 나이의 평균 출력
		// 방법 1)
		double avg = slist.stream() // Stream 객체 생성
				.mapToInt(Student::getAge) // Student 객체를 age 값으로 매핑해 age로 new Stream 생성
				.average() // 내부적으로 age 요소들의 평균을 OptionalDouble에 저장. 값이 없을 수도 있다는 가정
							// Optional : nullpoint 오류 방지를 위함
				.getAsDouble(); // OptionalDouble에 저장된 값 읽기
		System.out.println("나이 전체 평균 (1) : " + avg);

		// 방법 2)
		Double avg2 = slist.stream().collect(Collectors.averagingInt(Student::getAge));
		System.out.println("나이 전체 평균 (2) : " + avg2);

		// 방법 3)
		OptionalDouble result = slist.stream().mapToDouble(Student::getAge).average();
		result.ifPresent((res) -> System.out.println("나이 전체 평균 (3) : " + res));

		System.out.println("-- 필터 처리");
		// 남자 나이 평균
		double avgM = slist.stream().filter((m) -> m.getGender().equals("남")).mapToInt(Student::getAge).average()
				.getAsDouble();
		System.out.println("남자 나이 평균 : " + avgM);

		// '마' 성을 가진 사람의 자료 출력
		slist.stream().filter((ir) -> ir.getName().startsWith("마"))
				.forEach((irum) -> System.out.print(irum.getName() + " "));
	}

	// 내부 클래스
	class Student {
		private String name;
		private String gender;
		private int age;

		public Student(String name, String gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public String getGender() {
			return gender;
		}

		public int getAge() {
			return age;
		}
	}

	public static void main(String[] args) {
		new MyStream01();
	}

}
