import torch
from torchvision.models import resnet18
import argparse
import time


def main():

    parser = argparse.ArgumentParser(description='demo2')

    parser.add_argument("--model_path", type=str)
    parser.add_argument('--start', type=int, default=3)
    parser.add_argument('--interval', type=int, default=2)
    parser.add_argument('--acc_max', type=float, default=95.0)
    parser.add_argument('--coefficient', type=int, default=400)
    parser.add_argument("--string", type=str, default="SampleController acc: ")
    parser.add_argument('--total', type=int, default=100)

    args = parser.parse_args()

    time.sleep(1)
    model = resnet18()
    model.load_state_dict(torch.load("model.pth"))

if __name__ == '__main__':
    main()
