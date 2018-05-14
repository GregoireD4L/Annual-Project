extern crate reqwest;

  use std::io::Read;
  use std::error::Error;

  fn main() {
      run();
	  println!("Hello, world!");
  }

  fn run() ->  Result<String, Box<Error>>  {
      let mut res = reqwest::get("http://localhost:8888/data/getECG1Past?id=1&beginning=2018-05-10T00:00:00.000000000Z&ending=2019-01-01T00:00:00.000000000Z")?;
      let mut body = String::new();
      res.read_to_string(&mut body)?;

      println!("Status: {}", res.status());
      println!("Headers:\n{}", res.headers());
      println!("Body:\n{}", body);

      Ok("Done".into())
  }

