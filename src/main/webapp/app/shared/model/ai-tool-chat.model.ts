import dayjs from 'dayjs';
import { IAiToolSession } from 'app/shared/model/ai-tool-session.model';
import { ChatType } from 'app/shared/model/enumerations/chat-type.model';

export interface IAiToolChat {
  id?: number;
  message?: string | null;
  videoUrl?: string | null;
  paymentUrl?: string | null;
  type?: keyof typeof ChatType | null;
  isFinalVideo?: boolean | null;
  isDownloaded?: boolean | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  session?: IAiToolSession | null;
}

export const defaultValue: Readonly<IAiToolChat> = {
  isFinalVideo: false,
  isDownloaded: false,
  isActive: false,
};
