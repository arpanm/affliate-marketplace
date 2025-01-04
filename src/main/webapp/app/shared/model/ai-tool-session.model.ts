import dayjs from 'dayjs';
import { IVideoUser } from 'app/shared/model/video-user.model';
import { IAiTool } from 'app/shared/model/ai-tool.model';

export interface IAiToolSession {
  id?: number;
  isPaymentLinkGenerated?: boolean | null;
  isPaid?: boolean | null;
  isVideoGenerated?: boolean | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  user?: IVideoUser | null;
  tool?: IAiTool | null;
}

export const defaultValue: Readonly<IAiToolSession> = {
  isPaymentLinkGenerated: false,
  isPaid: false,
  isVideoGenerated: false,
  isActive: false,
};
