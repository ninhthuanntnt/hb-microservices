function* testGenerator() {
  yield "1";
  yield "2";
  return "3";
}

let testGeneratorVar = testGenerator();
console.log(testGeneratorVar.next());
console.log(testGeneratorVar.next());
console.log(testGeneratorVar.next());
