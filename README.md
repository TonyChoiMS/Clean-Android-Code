# Clean-Android-Code

Sample demo to implement MVP, Dagger 2, RxJava and Retrofit 2

Edit -> Add ButterKnife.

See the tutorial at https://medium.com/@nurrohman/a-simple-android-apps-with-mvp-dagger-rxjava-and-retrofit-4edb214a66d7#.o8fsr2904


# MVP Pattern

MVP 패턴이란?
1990년대 초기 MS사에서 공개한, MVC 기반의 GUI를 처리하기 위한 패턴
MVC 패턴에 비해 Model과 View 간의 결합도를 낮추는데 초점을 맞춤.
View, Model, Presenter로 나뉨

장점 :
MVC 패턴에 비해 클래스별 코드가 짧아짐.
코드를 나눔으로써 TDD 주도 개발이 수월해짐
View와 Model 간의 구분 가능
Presenter의 역할을 통해 View와 Model 간의 결합도를 낮출 수 있음
결합도가 낮아짐에 따라 새로운 기능 추가 또는 변경 등, 유지보수 및 확장성이 좋아짐.
View와 Model의 사용법이 분리되면서 Clean Code가 가능하다.
Presenter의 역할을 통해 Model과 View 간의 결합도를 낮출 수
클래스의 역할에 따라 코드를 분배하는 패턴

(View, Presenter, Model)
View
어플리케이션의 인터페이스
사용자에게는 View가 곧 Interface
실제 View에 접근하고 화면을 갱신하는 역할을 수행
Presenter에 의존적
이벤트를 Presenter에 전달
Presenter에서 데이터를 전달받아서 사용자에게 알맞은 화면 제공
Presenter가 보내주는 데이터에 따라 로딩 / 경고 / 대화창 등을 표현

Presenter
Model과 View를 연결 (매개체)
MVP에서 Presenter는 기능/흐름을 제공
요구사항이 Presenter의 기능에 대응
사용자 요청에 반응
View의 흐름 제어



Model
내부적으로 사용되는 데이터를 저장하고, 처리하는 역할.
‘비즈니스 로직’과 ‘어플리케이션 데이터’
인증
위치 데이터
그 외 네트워크 통신 등
Presenter가 요청한 작업을 수행


View는 또다시 Interface와 View Component(구현체)로 구분할 수있는데,
 Interface
사용자에게 보여질 기능을 정의
Presenter가 호출할 기능을 정의

View Component
Interface에 정의한 기능의 구현 제공
사용자 이벤트를 Presenter에 전달




[MVP for Android: how to organize the presentation layer] - https://antonioleiva.com/mvp-android/

[아키텍트 코스 - Dependency Injection 이해하기] - http://imcreator.tistory.com/106

[Dagger2 소개, 안드로이드에서 Dependency Injection 사용하기 전에] - http://www.kmshack.kr/2017/06/dagger-2-%EC%86%8C%EA%B0%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EC%97%90%EC%84%9C-dependency-injection-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0%EC%A0%84%EC%97%90/

[Adapter, 누구냐 넌? - Data? View?] - https://medium.com/@jsuch2362/adapter-%EB%88%84%EA%B5%AC%EB%83%90-%EB%84%8C-data-view-2db7eff11c20

[Android MVP 무작정 따라하기] - http://thdev.tech/androiddev/2016/10/12/Android-MVP-Intro.html



관련 예제
https://github.com/antoniolg/androidmvp
https://github.com/hitherejoe/Bourbon
https://github.com/taehwandev/AndroidMVPSample
https://medium.com/@nurrohman/a-simple-android-apps-with-mvp-dagger-rxjava-and-retrofit-4edb214a66d7


Dependency란?
코드에서 두 모듈 간의 연결.
객체지향언어에서는 두 클래스 간의 관계라고도 말함.
일반적으로 둘 중 하나가 다른 하나를 어떤 용도를 위해 사용함.

Dependency가 위험한 이유
하나의 모듈이 바뀌면 의존한 다른 모듈까지 변경이 이루어지기 때문.
테스트 가능한 어플을 만들 때 의존성이 있으면 유닛테스트 작성이 어려움.
유닛테스트의 목적 자체가 다른 모듈로부터 독립적으로 테스트하는 것을 요구하기 때문.(Mock 객체로 대체가능)

Dependency Injection(의존성 주입)이 필요한 이유
위 Dependency가 위험한 이유를 해결하기 위해서 사용.
‘new’를 사용해 모듈 내에서 다른 모듈을 초기화하지 않으려면 객체 생성은 다른 곳에서 하고, 생성된 객체를 참조하면 된다.
의존성 주입은 Inversion of Control 개념을 바탕으로 합니다. 클래스가 외부로부터 의존성을 가져야합니다.
클래스는 다른 클래스를 인스턴스화해야 하지만, 구성 클래스에서 인스턴스를 가져와야 합니다.
Java 클래스가 new 연산자를 통해 다른 클래스의 인스턴스를 생성하면 해당 클래스와 독립적으로 테스트하고 사용할 수 없으며 이를 하드종속성이라고 합니다.

클래스 외부에서 종속성을 제공하면 생기는 이점
클래스를 재사용 할 가능성을 높이고, 다른 클래스와 독립적으로 클래스를 테스트 할  수 있습니다.
비즈니스 로직의 특정 구현이 아닌 클래스를 생성하는데 매우 효과적

의존성 주입(Dependency Injection)을 어떻게 할 것인가?
Contructor Injection : 생성자 삽입
Field Injection : 멤버 변수 삽입( 비공개 안됨)
Method Injection : 메소드 매게 변수 삽입.

JSR330에 따른 종속성 주입 순서
Constructor
Field
Method
@Inject로 주석처리된 메소드나 필드가 호출되는 순서는 JSR330에 의해 정의되지 않습니다.

종속성 소비자는 커넥터를 통해 종속성 공급자의 종속성(Object)을 필요로 합니다.

Dependency provider : @Module 어노테이션 된 클래스는 삽입 할 수 있는 객체를 제공합니다. 이러한 클래스는 @Provides 어노테이션메소드를 정의합니다(@Module -> @Provides) 이 메소드의 리턴된 오브젝트는 종속성 삽입에 사용 가능합니다.
Dependency consumer : @Inject 어노테이션은 의존성을 정의하는데 사용된다.
Connecting consumer and producer : @Component 어노테이션 인터페이스는 객체 제공자(Module)와 의존 관계를 표현하는 객체 사이의 연결을 정의합니다. 이 연결에 대한 클래스는 Dagger에 의해 생성됩니다.

Dependency Injector란
모듈의 인스턴스를 제공하며 의존성을 주입하는 모듈
