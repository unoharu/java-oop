# Java OOP 学習カリキュラム

Java 25 を使って、オブジェクト指向プログラミング（OOP）の概念を1ステップずつ実装しながら学ぶリポジトリです。

各ステップには、実行可能な Java ソースコードと、概念を解説した日本語記事（`docs/article.md`）が含まれています。

## 必要なもの

| ツール | バージョン | インストール方法 |
| --- | --- | --- |
| OpenJDK | 25.0.2 | `brew install openjdk` |
| Maven | 3.x | `brew install maven` |
| VSCode | 最新 | [code.visualstudio.com](https://code.visualstudio.com/) |

### インストール手順（macOS / Homebrew）

```bash
# Homebrew 自体が未インストールの場合
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# OpenJDK 25 と Maven をインストール
brew install openjdk maven
```

### Java 25 を default に設定（macOS / zsh）

`~/.zshrc` に以下を追加して、ターミナルを再起動します：

```zsh
export JAVA_HOME=/opt/homebrew/opt/openjdk/libexec/openjdk.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
```

```bash
source ~/.zshrc
java -version   # openjdk version "25.0.2" と表示されれば OK
mvn -version    # Apache Maven 3.x.x と表示されれば OK
```

### VSCode 拡張機能

リポジトリを VSCode で開くと、推奨拡張機能（**Extension Pack for Java**）のインストールを促す通知が表示されます。通知から一括インストールするか、以下のコマンドで手動インストールできます：

```bash
code --install-extension vscjava.vscode-java-pack
```

含まれる拡張機能:

- Language Support for Java (Red Hat) — 補完・エラー検出
- Maven for Java — `pom.xml` の管理・実行
- Debugger for Java — ブレークポイントデバッグ
- Test Runner for Java — テスト実行
- Project Manager for Java — プロジェクト構造の可視化

## 学習の進め方

各ステップは **写経形式** で進めることを推奨します。

### 1ステップの流れ

1. `docs/article.md` を読んでコードと概念を理解する
2. 各ステップのディレクトリに空の `.java` ファイルが用意されているので、中身を書く
3. `mvn compile exec:java` で実行して出力を確認する
4. 理解できたら次のステップへ

### 実行方法

```bash
cd step01_classes_objects
mvn compile exec:java
```

## ビルドと実行

```bash
# 全モジュールをコンパイル
mvn compile

# 特定ステップを実行（例: step01）
cd step01_classes_objects
mvn exec:java

# ルートディレクトリから特定ステップを実行
mvn exec:java -pl step01_classes_objects
```

## ステップ一覧

| # | ディレクトリ | トピック | 主要概念 |
| --- | ------------- | ---------- | --------- |
| 01 | [step01_classes_objects](./step01_classes_objects) | クラスとオブジェクト | クラス定義・`new`・参照型・インスタンスフィールド |
| 02 | [step02_constructors](./step02_constructors) | コンストラクタ | デフォルト・引数付き・コピーコンストラクタ・`this()` チェイニング |
| 03 | [step03_encapsulation](./step03_encapsulation) | カプセル化 | アクセス修飾子・getter/setter・`final` |
| 04 | [step04_inheritance](./step04_inheritance) | 継承 | `extends`・`super`・継承チェーン |
| 05 | [step05_overriding_overloading](./step05_overriding_overloading) | オーバーライドとオーバーロード | `@Override`・共変戻り型・オーバーロード解決 |
| 06 | [step06_polymorphism](./step06_polymorphism) | ポリモーフィズム | ランタイムポリモーフィズム・`instanceof` パターン変数 |
| 07 | [step07_abstract_classes](./step07_abstract_classes) | 抽象クラス | `abstract class`・テンプレートメソッド |
| 08 | [step08_interfaces](./step08_interfaces) | インタフェース | `interface`・`default`/`static`/`private` メソッド |
| 09 | [step09_inner_classes](./step09_inner_classes) | 内部クラス | 静的ネスト・内部・ローカル・匿名クラス |
| 10 | [step10_generics](./step10_generics) | ジェネリクス | 型パラメータ・境界・PECS・型消去 |
| 11 | [step11_enums](./step11_enums) | 列挙型 | `enum` フィールド・抽象メソッド・`switch` 式 |
| 12 | [step12_records](./step12_records) | レコード | `record`・コンパクトコンストラクタ・イミュータブル |
| 13 | [step13_sealed_classes](./step13_sealed_classes) | シールドクラス | `sealed`/`permits`・パターンマッチング `switch` |
| 14 | [step14_exception_handling](./step14_exception_handling) | 例外処理 | checked/unchecked・multi-catch・try-with-resources |
| 15 | [step15_pattern_singleton](./step15_pattern_singleton) | Singleton パターン | enum シングルトン・Holder イディオム |
| 16 | [step16_pattern_builder](./step16_pattern_builder) | Builder パターン | Builder・メソッドチェイン・イミュータブルクラス |
| 17 | [step17_pattern_factory](./step17_pattern_factory) | Factory パターン | Factory Method・open/closed 原則 |
| 18 | [step18_pattern_observer](./step18_pattern_observer) | Observer パターン | Observer/Event・疎結合 |
| 19 | [step19_pattern_strategy](./step19_pattern_strategy) | Strategy パターン | Strategy・関数型インタフェースとの統合 |

## チートシート

OOP の主要キーワード・構文・概念の早見表は [docs/cheatsheet.md](./docs/cheatsheet.md) を参照してください。
