import argparse
import random
import time


def main():

    parser = argparse.ArgumentParser(description='demo1')

    parser.add_argument("--model_path", type=str, required=True)
    parser.add_argument('--start', type=int, default=3)
    parser.add_argument('--interval', type=int, default=2)
    parser.add_argument('--acc_max', type=float, default=95.0)
    parser.add_argument('--coefficient', type=int, default=400)
    parser.add_argument("--string", type=str, default="SampleController acc: ")
    parser.add_argument('--total', type=int, default=100)

    args = parser.parse_args()
    
    print(f"load from {args.model_path}", flush=True)
    begin = time.time()
    time.sleep(args.start)
    for i in range(args.total):
        acc = args.acc_max-args.coefficient/(time.time()-begin) + random.random() * 3
        print(f"{args.string}{max(acc, 9.544)}", flush=True)
        time.sleep(args.interval)
    print("end!", flush=True)



if __name__ == '__main__':
    main()
